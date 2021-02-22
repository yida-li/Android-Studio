package com.example.tutorial4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.tutorial4.Database.DatabaseHelper;
import com.example.tutorial4.Model.Course;
import com.example.tutorial4.Model.Assignment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AssignmentActivity extends AppCompatActivity {

    private EditText courseInfoText;
    protected ListView assignmentListView;
    protected DatabaseHelper dbHelper;
    private Button deleteButton;
    protected FloatingActionButton addAssignmentButton;
    protected List<Assignment> assignments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        courseInfoText = findViewById(R.id.courseInfoText);
        assignmentListView= findViewById(R.id.assignmentListView);
        addAssignmentButton= findViewById(R.id.AddAssignmentFloatingActionButton);
        deleteButton= findViewById(R.id.deleteButton);



        courseInfoText = findViewById(R.id.courseInfoText);

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING_I_NEED");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }

       /* String newKey;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newKey= null;
            } else {
                newKey= extras.getString("KEY_I_NEED");
            }
        } else {
            newKey= (String) savedInstanceState.getSerializable("KEY_I_NEED");
        } deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteCourse(Integer.parseInt(newKey));
            } });
*/
        courseInfoText.setText(newString);





        addAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertAssignmentDialog dialog = new InsertAssignmentDialog();
                dialog.show(getSupportFragmentManager(),"Insert Course");
            }
        });
        dbHelper= new DatabaseHelper(this);

        loadAssignments();

    }








    void loadAssignments(){
        assignments =  dbHelper.getAllAssignments();
        List<String> assignmentStringList = new ArrayList<>();
        for(int i =0; i < assignments.size(); i++)
        {
            assignmentStringList.add(assignments.get(i).toString() + "\n\n");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,assignmentStringList);
        assignmentListView.setAdapter(adapter);

        }


}

