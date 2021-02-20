package com.example.tutorial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {



    private EditText nameEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameEditText = findViewById(R.id.nameEditText);
        saveButton = findViewById(R.id.saveButton);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditText.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.profileSharedPreferencesFile), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.profileNameKeySharedPreferences),name);
                editor.apply();


                Toast toast = Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_LONG);
                toast.show(); //boilers plates stack on stack bundle on bundle library through libraries

            }
        });



    }
}