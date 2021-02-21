package com.example.tutorial5.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.tutorial5.Model.Course;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


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


    public long insertCourse(Course course){
        long id = -1;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_COURSE_TITLE, course.getTitle());

        contentValues.put(Config.COLUMN_COURSE_CODE, course.getCode());
        //db.insert()
        try{
            id = db.insertOrThrow(Config.TABLE_COURSE_NAME,null,contentValues);
        }catch(SQLException e){
            Log.d(TAG," exception :  " + e.getMessage());
            Toast.makeText(context,"Operation failed" + e.getMessage(),Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }


        return id;

    }



    public List<Course> getAllCourses(){
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursor = null;
        try {

            cursor = db.query(Config.TABLE_COURSE_NAME,null,null,null,null,null,null);

            if( cursor != null)
            {
                if ( cursor.moveToFirst()){
                    // if the the list is not empty so move the magical pointer to the top to do something in this case

                    List<Course> courseList = new ArrayList<>();
                    do {
                        // getting information from cursor
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_TITLE));
                        String code = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_CODE));


                        // creating a new Course object with the information


                        // adding this course object to courseList
                        courseList.add(new Course(id,title,code));

                    } while(cursor.moveToNext());
                    return courseList;
                }
            }

            List<Course> courseList = new ArrayList<>();

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
