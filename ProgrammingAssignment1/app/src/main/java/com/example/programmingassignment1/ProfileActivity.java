package com.example.programmingassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private EditText studentName;
    private EditText studentAge;
    private EditText studentID;
    private Button saveButton;
    private Button editButton;
    private SharedPreferenceHelper studentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        studentHelper = new SharedPreferenceHelper(this);
        studentName=findViewById(R.id.studentName);
        studentAge=findViewById(R.id.studentAge);
        studentID=findViewById(R.id.studentID);

        saveButton=findViewById(R.id.saveButton);
        editButton=findViewById(R.id.editButton);
        saveButton.setVisibility(View.VISIBLE);
        editButton.setVisibility(View.GONE);


        String name =studentHelper.getName();
        String age =studentHelper.getAge();
        String id =studentHelper.getId();
        if(name !=null ) {
            studentName.setText(name);
            studentAge.setText(age);
            studentID.setText(id);
            saveButton.setVisibility(View.GONE);
            editButton.setVisibility(View.VISIBLE);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = studentName.getText().toString();
                String id = studentID.getText().toString();
                String age = studentAge.getText().toString();
                studentHelper.saveName(name,id,age);
                Toast toast = Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG);
                toast.show();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = studentName.getText().toString();
                String id = studentID.getText().toString();
                String age = studentAge.getText().toString();
                studentHelper.saveName(name,id,age);
                Toast toast = Toast.makeText(getApplicationContext(),"Edited",Toast.LENGTH_LONG);
                toast.show();
            }
        });



    }
}