package com.example.tutorial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    final String TAG ="ProfileActivity";

    private EditText NameEditText;
    private Button saveButton;



    private SharedPreferenceHelper help;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.i(TAG,"On create is acccessed");




        help = new SharedPreferenceHelper(this);
        NameEditText=findViewById(R.id.NameEditText);

        String name =help.getName();
        if(name !=null)
            NameEditText.setText(name);


        saveButton=findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get the text from the editText
                // save to sharedPreferences hopefully

                String name = NameEditText.getText().toString();


                help.saveName(name);


                Toast toast = Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG);
                toast.show();


            }
        });



    }
}