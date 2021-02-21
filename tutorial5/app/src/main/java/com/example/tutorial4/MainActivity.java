package com.example.tutorial4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tutorial4.Database.DatabaseHelper;
import com.example.tutorial4.Model.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    protected ListView coursesListView;
    protected DatabaseHelper dbHelper;

    protected FloatingActionButton addCourseButton;
    protected List<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coursesListView = findViewById(R.id.coursesListView);
        addCourseButton = findViewById(R.id.AddCourseFloatingActionButton);



        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InsertCourseDialog dialog = new InsertCourseDialog();
                dialog.show(getSupportFragmentManager(),"Insert Course");

            }
        });
        dbHelper= new DatabaseHelper(this);





    loadCourses();


    }

    void loadCourses(){
       courses =  dbHelper.getAllCourses();
        List<String> coursesStringList = new ArrayList<>();
        for(int i =0; i < courses.size(); i++)
        {
            coursesStringList.add(courses.get(i).toString() + "\n\n");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,coursesStringList);
        coursesListView.setAdapter(adapter);
    }
}