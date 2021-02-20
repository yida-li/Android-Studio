package com.example.tutorial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        profileButton = findViewById(R.id.profileButton);


        profileButton.setOnClickListener(new View.OnClickListener() {     // button inherits from view or vice versa
            @Override
            public void onClick(View v) {
                goToProfileActivity();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.profileSharedPreferencesFile), Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(getString(R.string.profileNameKeySharedPreferences),null);

        if( name == null){

            goToProfileActivity();
        }
        else {

            profileButton.setText(name);
        }

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

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.profileSharedPreferencesFile), Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(getString(R.string.profileNameKeySharedPreferences),null);

        if( name == null){

            goToProfileActivity();
        }
        else {

            profileButton.setText(name);
        }

    }

    protected void goToProfileActivity(){
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }

}