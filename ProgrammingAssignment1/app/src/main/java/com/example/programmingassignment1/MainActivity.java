package com.example.programmingassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button profileButton;
    private Button gradeButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUserInterface();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferenceHelper spHelper = new SharedPreferenceHelper(this);
        String name = spHelper.getName();
        if(name== null){
            goToProfileActivity();
        }else{
            profileButton.setText(name);
        }

    }

    protected void setUpUserInterface(){

        profileButton= findViewById(R.id.profileButton);
        gradeButton= findViewById(R.id.gradeButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfileActivity();
            }
        });
        gradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGradeActivity();
            }
        });

        SharedPreferenceHelper spHelper = new SharedPreferenceHelper(this);
        String name = spHelper.getName();
        if(name== null){
            goToProfileActivity();
        }else{
            profileButton.setText(name);
        }


    }

    protected void goToProfileActivity()
    {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    protected void goToGradeActivity()
    {
        Intent intent = new Intent(this, GradeActivity.class);
        startActivity(intent);
    }

}