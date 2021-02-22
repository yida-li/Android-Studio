package com.example.tutorial4;

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

import com.example.tutorial4.Database.DatabaseHelper;
import com.example.tutorial4.Model.Assignment;
import com.example.tutorial4.Model.Course;

import java.util.Objects;

public class InsertAssignmentDialog extends DialogFragment {


    private EditText assignmentNumberEditText;
    private EditText assignmentGradeEditText;

    private Button saveAssignmentButton;
    private Button cancelAssignmentButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_assignment, container);

        assignmentNumberEditText= view.findViewById(R.id.assignmentNumberEditText);
        assignmentGradeEditText = view.findViewById(R.id.assignmentGradeEditText);

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

                String assignmentID= assignmentNumberEditText.getText().toString();
                String assignmentGrade = assignmentGradeEditText.getText().toString();


                Assignment assignment = new Assignment(assignmentID,assignmentGrade);

                
                

                if(!((assignmentID).equals("")  || (assignmentGrade).equals("")))
                {
                    DatabaseHelper dbHelper= new DatabaseHelper(getActivity());
                    dbHelper.insertAssignment(assignment);


                    // bugged
                    ((AssignmentActivity)getActivity()).loadAssignments();


                    getDialog().dismiss();
                }
            }
        });


        return view;
    }
}
