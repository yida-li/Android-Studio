package com.example.tutorial5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.tutorial5.Database.DatabaseHelper;
import com.example.tutorial5.Model.Course;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView coursesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coursesTextView = findViewById(R.id.coursesTextView);


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.insertCourse(new Course(-1, "COEN", "311"));
        dbHelper.insertCourse(new Course(-1, "COEN", "352"));
        dbHelper.insertCourse(new Course(-1, "COEN", "346"));
        dbHelper.insertCourse(new Course(-1, "COEN", "316"));
        dbHelper.insertCourse(new Course(-1, "COEN", "317"));

        List<Course> courses =  dbHelper.getAllCourses();
        String coursesString = "";
        for(int i =0; i < courses.size(); i++)
        {
            coursesString += courses.get(i).toString() + "\n\n";
        }

        coursesTextView.setText(coursesString); // display string holding all data in any textView


    }
}