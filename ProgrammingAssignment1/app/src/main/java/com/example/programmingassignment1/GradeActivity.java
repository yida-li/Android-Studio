package com.example.programmingassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GradeActivity extends AppCompatActivity {

    private Button gradeButton;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        getSupportActionBar().setTitle("Grades");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView=findViewById(R.id.listView);
        gradeButton=findViewById(R.id.gradeButton);


        // two String arrays holding the numeric & letter grade
        ArrayList<String> arrayListGradeMark = new ArrayList<>();
        ArrayList<String> arrayListGradeLetter = new ArrayList<>();





        // random course number from 1-5
        Random r = new Random();
        int i1 = r.nextInt(6 - 1) + 1;

        // 1 space for button on top
        arrayListGradeLetter.add("\n");
        arrayListGradeMark.add("\n");


        for( int i=0;i<i1;i++){
            arrayListGradeMark.add("course "+(i+1));
            arrayListGradeLetter.add("course "+(i+1));

            // random assignment number from 1-4;
            Random assignment = new Random();
            int i2 = assignment.nextInt(5 - 1) + 1;

            for( int j=0;j<i2;j++){

                // random assignment mark from 100-1;
                Random mark = new Random();
                int i3 = mark.nextInt(101 - 1) + 1;
                arrayListGradeMark.add("assignment "+(j+1) + "    grade : "+(i3));

                if (i3 >= 100 && i3 <= 85) {
                    arrayListGradeLetter.add("assignment "+(j+1) + "    grade : A");
                } else if (i3 >= 75 && i3 <= 84) {
                    arrayListGradeLetter.add("assignment "+(j+1) + "    grade : B");
                } else if (i3 >= 55 && i3 <= 74) {
                    arrayListGradeLetter.add("assignment "+(j+1) + "    grade : C");
                } else if (i3 >= 45 && i3 <= 54) {
                    arrayListGradeLetter.add("assignment "+(j+1) + "    grade : D");
                } else {
                    arrayListGradeLetter.add("assignment " + (j + 1) + "    grade : F");
                }

            }
        }


        ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayListGradeMark);
        //ArrayAdapter arrayAdapter2= new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayListGradeLetter);
        listView.setAdapter(arrayAdapter);


        /* gradeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                ArrayAdapter arrayAdapter2= new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayListGradeLetter);
                listView.setAdapter(arrayAdapter);

                could not get the button to change between the 2 lists....
            }
        });*/





    }
}