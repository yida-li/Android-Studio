package com.example.tutorial4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tutorial4.Database.DatabaseHelper;
import com.example.tutorial4.Model.Course;

import java.util.Objects;

public class InsertCourseDialog extends DialogFragment {


private EditText courseTitleEditText;
private EditText courseCodeTitleEditText;
private Button saveButton;
private Button cancelButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_course, container);

        courseTitleEditText= view.findViewById(R.id.courseTitleEditText);
        courseCodeTitleEditText = view.findViewById(R.id.courseCodeEditText);
        saveButton = view.findViewById(R.id.saveButton);
        cancelButton = view.findViewById(R.id.cancelButton);


        cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Objects.requireNonNull(getDialog()).dismiss();
                    }
                });

    saveButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String courseTitle= courseTitleEditText.getText().toString();
            String courseCode = courseCodeTitleEditText.getText().toString();

            Course course= new Course(-1,courseTitle,courseCode);



                    if( !(courseTitle.equals("") || courseCode.equals("")))
                    {
                        DatabaseHelper dbHelper= new DatabaseHelper(getActivity());
                        dbHelper.insertCourse(course);


                        //navigate back without having to refresh app
                        ((MainActivity)getActivity()).loadCourses();


                        getDialog().dismiss();
                    }
        }
    });


        return view;
    }
}
