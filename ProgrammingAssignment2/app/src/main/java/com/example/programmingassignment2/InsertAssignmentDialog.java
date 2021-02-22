package com.example.programmingassignment2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.programmingassignment2.Model.Assignments;
import com.example.programmingassignment2.Database.DatabaseHelper;
import com.example.programmingassignment2.Model.Assignments;
import com.example.programmingassignment2.Model.Course;

import java.util.Objects;

public class InsertAssignmentDialog extends DialogFragment {


    private EditText assignmentNumberEditText;
    private EditText assignmentGradeEditText;
    private EditText assignmentKeyEditText;
    private Button saveAssignmentButton;
    private Button cancelAssignmentButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_assignment, container);

        assignmentNumberEditText= view.findViewById(R.id.assignmentNumberEditText);
        assignmentGradeEditText = view.findViewById(R.id.assignmentGradeEditText);
        assignmentKeyEditText = view.findViewById(R.id.assignmentKeyEditText);
        saveAssignmentButton = view.findViewById(R.id.saveAssignmentButton);
        cancelAssignmentButton = view.findViewById(R.id.cancelAssignmentButton);


        cancelAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        saveAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(assignmentNumberEditText.getText().toString());
                int grade = Integer.parseInt(assignmentGradeEditText.getText().toString());
                int key = Integer.parseInt(assignmentKeyEditText.getText().toString());


                Assignments assignment = new Assignments(key,id,grade);




                if(!(String.valueOf(id).equals("")  || String.valueOf(grade).equals("")))
                {
                    DatabaseHelper dbHelper= new DatabaseHelper(getActivity());
                    dbHelper.insertAssignment(assignment);

                    ((MainActivity)getActivity()).loadCourses();




                    getDialog().dismiss();
                }
            }
        });


        return view;
    }
}
