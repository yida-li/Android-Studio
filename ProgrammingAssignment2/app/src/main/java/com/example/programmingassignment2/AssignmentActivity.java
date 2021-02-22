package com.example.programmingassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.programmingassignment2.Database.DatabaseHelper;
import com.example.programmingassignment2.Model.Assignments;
import com.example.programmingassignment2.Model.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AssignmentActivity extends AppCompatActivity {

    private EditText courseInfoText;

    protected ListView assignmentListView;
    protected DatabaseHelper dbHelper;
    protected FloatingActionButton addAssignmentButton;
    protected List<Assignments> assignments;
    private Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        assignmentListView = findViewById(R.id.assignmentListView);
        addAssignmentButton = findViewById(R.id.AddAssignmentFloatingActionButton);
        deleteButton= findViewById(R.id.deleteButton);

        courseInfoText = findViewById(R.id.courseInfoText);


        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                newString = extras.getString("STRING_I_NEED");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }


        String newKEY;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newKEY = null;
            } else {
                newKEY = extras.getString("ID_I_NEED");
            }
        } else {
            newKEY = (String) savedInstanceState.getSerializable("ID_I_NEED");
        }

        int temp= Integer.parseInt(newKEY);
        courseInfoText.setText(newString+" "+temp);



      /*  loadAssignments();*/

        addAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertAssignmentDialog dialog = new InsertAssignmentDialog();
                dialog.show(getSupportFragmentManager(), "Insert Assignment");
            }
        });dbHelper = new DatabaseHelper(this);
        /*deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteCourse(temp);

            }
        });*/



    }
/*
    void loadAssignments() {
        assignments = dbHelper.getAllAssignments();
        List<String> assignmentStringList = new ArrayList<>();
        for (int i = 0; i < assignments.size(); i++) {
            assignmentStringList.add(assignments.get(i).toString() + "\n\n");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assignmentStringList);
        assignmentListView.setAdapter(adapter);
    }*/
}