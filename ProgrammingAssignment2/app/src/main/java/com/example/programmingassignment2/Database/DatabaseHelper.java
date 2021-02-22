package com.example.programmingassignment2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import com.example.programmingassignment2.Model.Assignments;
import com.example.programmingassignment2.Model.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = " DATA base helper";
    private static final String DATABASE_NAME = Config.DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_COURSE_TABLE = "CREATE TABLE " + Config.TABLE_COURSE_NAME + " ("
                + Config.COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_COURSE_TITLE + " TEXT NOT NULL, "
                + Config.COLUMN_COURSE_CODE + " TEXT NOT NULL"
                + ")";

        Log.d(TAG, "Table created with this query: " + CREATE_COURSE_TABLE);

        db.execSQL(CREATE_COURSE_TABLE);

        Log.d(TAG, "course table created ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long insertCourse(Course course) {
        long id = -1;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_COURSE_TITLE, course.getTitle());

        contentValues.put(Config.COLUMN_COURSE_CODE, course.getCode());
        //db.insert()
        try {
            id = db.insertOrThrow(Config.TABLE_COURSE_NAME, null, contentValues);
        } catch (SQLException e) {
            Log.d(TAG, " exception :  " + e.getMessage());
            Toast.makeText(context, "Operation failed" + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }


        return id;

    }

    // get 1 specific course
    public Course getTodo(long todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + Config.TABLE_COURSE_NAME + " WHERE "
                + Config.COLUMN_COURSE_ID + " = " + todo_id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Course course = new Course();
        course.setId(c.getInt(c.getColumnIndex(Config.COLUMN_COURSE_ID)));
        course.setCode((c.getString(c.getColumnIndex(Config.COLUMN_COURSE_CODE))));
        course.setTitle(c.getString(c.getColumnIndex(Config.COLUMN_COURSE_TITLE)));

        return course;
    }

    /**
     * Deleting a todo
     */
    public void deleteCourse(long tado_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Config.TABLE_COURSE_NAME, Config.COLUMN_COURSE_ID + " = ?",
                new String[] { String.valueOf(tado_id) });
    }



    public long insertAssignment(Assignments assignment) {
        long id = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_ASSIGNMENT_Key, assignment.getKey());
        contentValues.put(Config.COLUMN_ASSIGNMENT_ID, assignment.getId());
        contentValues.put(Config.COLUMN_ASSIGNMENT_GRADE, assignment.getGradePoints());
        //db.insert()
        try {
            id = db.insertOrThrow(Config.TABLE_ASSIGNMENT_NAME, null, contentValues);
        } catch (SQLException e) {
            Log.d(TAG, " exception :  " + e.getMessage());
            Toast.makeText(context, "Operation failed" + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }


        return id;

    }

    public List<Course> getAllCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(Config.TABLE_COURSE_NAME, null, null, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    // if the the list is not empty so move the magical pointer to the top to do something in this case

                    List<Course> courseList = new ArrayList<>();
                    do {
                        // getting information from cursor
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_TITLE));
                        String code = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_CODE));

                        courseList.add(new Course(id, title, code));

                    } while (cursor.moveToNext());
                    return courseList;
                }
            }

        } catch (Exception e) {
            Log.d(TAG, "Exception :" + e.getMessage());
        } finally {
            if (cursor != null)
                cursor.close();
        }

        // in case of errors
        return Collections.emptyList();
    }
    public List<Assignments> getAllAssignments(){
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursor = null;
        try {

            cursor = db.query(Config.TABLE_ASSIGNMENT_NAME,null,null,null,null,null,null);

            if( cursor != null)
            {
                if ( cursor.moveToFirst()){


                    List<Assignments> assignment = new ArrayList<>();
                    do {
                        // getting information from cursor
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_ID));
                        int key = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_Key));
                        int grade = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_GRADE));


                        // creating a new Course object with the information


                            assignment.add(new Assignments(key, id, grade));

                    } while(cursor.moveToNext());
                    return assignment;
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

