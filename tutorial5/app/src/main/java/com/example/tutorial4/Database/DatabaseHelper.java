package com.example.tutorial4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.tutorial4.Model.Assignment;
import com.example.tutorial4.Model.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.tutorial4.Database.Config.COLUMN_ASSIGNMENT_GRADE;
import static com.example.tutorial4.Database.Config.COLUMN_ASSIGNMENT_ID;
import static com.example.tutorial4.Database.Config.COLUMN_COURSE_CODE;
import static com.example.tutorial4.Database.Config.COLUMN_COURSE_ID;
import static com.example.tutorial4.Database.Config.COLUMN_COURSE_TITLE;
import static com.example.tutorial4.Database.Config.TABLE_ASSIGNMENT_NAME;
import static com.example.tutorial4.Database.Config.TABLE_COURSE_NAME;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG= " DATA base helper";
    private static final String DATABASE_NAME = Config.DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null,  DATABASE_VERSION );
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ASSIGNMENT_TABLE = "CREATE TABLE " + TABLE_ASSIGNMENT_NAME + " ("
                + Config.COLUMN_ASSIGNMENT_ID + " TEXT NOT NULL, "
                + Config.COLUMN_ASSIGNMENT_GRADE + " TEXT NOT NULL"

                + ")";


        db.execSQL(CREATE_ASSIGNMENT_TABLE);


        String CREATE_COURSE_TABLE = "CREATE TABLE " + Config.TABLE_COURSE_NAME + " ("
                + Config.COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_COURSE_TITLE + " TEXT NOT NULL, "
                + Config.COLUMN_COURSE_CODE + " TEXT NOT NULL"
                + ")";

        db.execSQL(CREATE_COURSE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long insertCourse(Course course)
    {
        long id = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_COURSE_TITLE, course.getTitle());
        contentValues.put(Config.COLUMN_COURSE_CODE, course.getCode());
        try {
            id = db.insertOrThrow(Config.TABLE_COURSE_NAME, null, contentValues);
        } catch(SQLiteException e) {
            Log.d(TAG, "Exception: " + e.getMessage());
            Toast.makeText(context, "Operation Failed: "+ e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        return id;
    }



        // insert an assignment into sql table
    public long insertAssignment( Assignment assignment){
        long id = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_ASSIGNMENT_GRADE,assignment.getGrade());
        contentValues.put(Config.COLUMN_ASSIGNMENT_ID, assignment.getId());


        try{
            id = db.insertOrThrow(TABLE_ASSIGNMENT_NAME,null,contentValues);
        }catch(SQLException e){
            Log.d(TAG," exception :  " + e.getMessage());
            Toast.makeText(context,"Operation failed" + e.getMessage(),Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }


        return id;

    }


    // delete a course
    public void deleteCourse(long tado_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Config.TABLE_COURSE_NAME, Config.COLUMN_COURSE_ID + " = ?",
                new String[] { String.valueOf(tado_id) });
    }

    // delete an assignment
    public void deleteAssignment(long tado_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ASSIGNMENT_NAME, Config.COLUMN_ASSIGNMENT_ID + " = ?",
                new String[] { String.valueOf(tado_id) });
    }


    // returns a course given a unique KEY
    public Course getTodo(long todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_COURSE_NAME + " WHERE "
                + COLUMN_COURSE_ID + " = " + todo_id;



        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Course course = new Course();
        course.setId(c.getInt(c.getColumnIndex(COLUMN_COURSE_ID)));
        course.setCode((c.getString(c.getColumnIndex(COLUMN_COURSE_CODE))));
        course.setTitle(c.getString(c.getColumnIndex(COLUMN_COURSE_TITLE)));

        return course;
    }







    public List<Course> getAllCourses(){
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursor = null;
        try {

            cursor = db.query(TABLE_COURSE_NAME,null,null,null,null,null,null);

            if( cursor != null)
            {
                if ( cursor.moveToFirst()){
                    // if the the list is not empty so move the magical pointer to the top to do something in this case

                    List<Course> courseList = new ArrayList<>();
                    do {
                        // getting information from cursor
                        int id = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE_ID));
                        String title = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_TITLE));
                        String code = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_CODE));


                        // creating a new Course object with the information


                        // adding this course object to courseList
                        courseList.add(new Course(id,title,code));

                    } while(cursor.moveToNext());
                    return courseList;
                }
            }



        }catch (Exception e){

            Log.d(TAG, "Exception :" + e.getMessage());

        }finally {
            if( cursor !=null)
                cursor.close();
        }

        // in case of errors
        return Collections.emptyList();
    }


    public List<Assignment> getAllAssignments(){
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursor = null;
        try {

            cursor = db.query(TABLE_ASSIGNMENT_NAME,null,null,null,null,null,null);

            if( cursor != null)
            {
                if ( cursor.moveToFirst()){
                    // if the the list is not empty so move the magical pointer to the top to do something in this case

                    List<Assignment> assignmentList = new ArrayList<>();
                    do {
                        String id = cursor.getString(cursor.getColumnIndex(COLUMN_ASSIGNMENT_ID));
                        String grade = cursor.getString(cursor.getColumnIndex(COLUMN_ASSIGNMENT_GRADE));


                        assignmentList.add(new Assignment(id,grade));

                    } while(cursor.moveToNext());
                    return assignmentList;
                }
            }



        }catch (Exception e){

            Log.d(TAG, "Exception :" + e.getMessage());

        }finally {
            if( cursor !=null)
                cursor.close();
        }

        // in case of errors
        return Collections.emptyList();
    }


}
