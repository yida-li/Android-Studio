package com.example.tutorial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {





    private Button profileButton;

    final String TAG = "MainActivity";


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,
                "On Start is accesss");    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,
                "On Stop is accessed ");    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,
                "On Destroy is accessed ");    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,
                "On Pause is accessed ");    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,
                "On Resume is accessed ");

        // if it doesnt exist, it will make/open sesame
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.profileSharedPreferencesFile), Context.MODE_PRIVATE);
        String name =  sharedPreferences.getString(getString(R.string.profileNameKeySharedPreferences), null)   ; // default value in shareprference is null o.o
        //hover mouse here  ^ to change resource name p.s


        if(name== null){

            goToProfileActivity();  // force user to go to activity to store a namu

        }else{
            profileButton.setText(name);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG,"On Create is accessed ");
            profileButton = findViewById(R.id.profileButton);


            profileButton.setOnClickListener(new View.OnClickListener() {   // button inherits from view L
                @Override
                public void onClick(View v) {
                    goToProfileActivity();
                }
            });
/*
        // if it doesnt exist, it will make/open sesame
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.profileSharedPreferencesFile), Context.MODE_PRIVATE);
        String name =  sharedPreferences.getString(getString(R.string.profileNameKeySharedPreferences), null)   ; // default value in shareprference is null o.o
        //hover mouse here  ^ to change resource name p.s
*/
        SharedPreferenceHelper spHelper = new SharedPreferenceHelper(this);

        String name = spHelper.getName();

        if(name== null){

            goToProfileActivity();  // force user to go to activity to store a namu

        }else{
            profileButton.setText(name);
        }





    }




    protected void goToProfileActivity()
    {

        Intent intent = new Intent(this, ProfileActivity.class);

        startActivity(intent);


    }



}