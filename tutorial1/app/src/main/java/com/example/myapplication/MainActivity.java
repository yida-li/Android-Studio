package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private Button goToButton;
    private Button Eden;
    private EditText sayHelloEditText;
    private TextView sandcastle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();

    }
    protected void setUpUI(){


        goToButton= findViewById(R.id.goToButton);
        sayHelloEditText = findViewById(R.id.sayHelloEditText);
        Eden= findViewById(R.id.Eden);
        sandcastle = findViewById(R.id.sandcastle);

        goToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHelloActivity();
            }
        });
        Eden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandcastle.setText("yo");
            }
        });

    }
    protected void goToHelloActivity()
    {

        Intent intent = new Intent( this, helloActivity.class);  // kinda of like a reference to child activity right?


        String message = sayHelloEditText.getText().toString();

        intent.putExtra(getString(R.string.yida), message);
        startActivity(intent);

    }

}
