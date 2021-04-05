package com.example.mystoragecenter;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.sql.Array;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    ValuesStorage storehelper;

    private Button btnAdd, btnViewData,button;
    private EditText editText,editText2,editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        button = (Button) findViewById(R.id.button);

        mDatabaseHelper = new DatabaseHelper(this);
        storehelper= new ValuesStorage(this);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    //store(newEntry);
                   addValues(newEntry);
                    editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                String newEntry2 = editText2.getText().toString();
                String newEntry3 = editText3.getText().toString();
                if (editText.length() != 0) {
                    store(newEntry,newEntry2,newEntry3);
                    //addValues(newEntry);
                    editText.setText("");
                    editText2.setText("");
                    editText3.setText("");

                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
               // Intent intent = new Intent(MainActivity.this, ListStorage.class);
                startActivity(intent);
            }
        });

    }


    public void addValues(String newEntry) {
        boolean insertData = mDatabaseHelper.addValues(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong here");
        }
    }

    public void store(String newEntry,String newEntry2,String newEntry3) {
        boolean insertData = storehelper.addValues(newEntry,newEntry2,newEntry3);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong here fere");
        }
    }



    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}