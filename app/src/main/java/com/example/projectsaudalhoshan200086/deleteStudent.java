package com.example.projectsaudalhoshan200086;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;

public class deleteStudent extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://projectapp-ca005-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);
        EditText id = findViewById(R.id.inputID3);
        Button delete = findViewById(R.id.deleteBttn);

        CheckBox idCheck = findViewById(R.id.idCheck);
        CheckBox nameCheck = findViewById(R.id.nameCheck);
        CheckBox surCheck = findViewById(R.id.surCheck);
        CheckBox fatherCheck = findViewById(R.id.fatherCheck);
        CheckBox dobCheck = findViewById(R.id.dobCheck);
        CheckBox nationalCheck = findViewById(R.id.nationalCheck);
        CheckBox genderCheck = findViewById(R.id.genderCheck);
        Button deleteCheck = findViewById(R.id.deleteCheck);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idVal = id.getText().toString();
                if (idVal.matches("")) {
                    Toasty.error(getBaseContext(), "Please Input ID to delete Student", Toast.LENGTH_SHORT, true).show();
                }else {
                    deleteStudent(idVal);
                }
            }
        });

        deleteCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idVal = id.getText().toString();
                if (idVal.matches("")) {
                    Toasty.error(getBaseContext(), "Please Input ID to delete Records", Toast.LENGTH_SHORT, true).show();
                }else {
                    deleteRecords(idVal,idCheck,nameCheck,surCheck,fatherCheck,dobCheck,nationalCheck,genderCheck);
                }
            }
        });
    }
    public void deleteStudent(String id){
        myRef.child("Student").child(id).removeValue();
        Toasty.success(getBaseContext(), "Student Information Deleted", Toast.LENGTH_SHORT, true).show();
    }
    public void deleteRecords(String id, CheckBox idCheck, CheckBox nameCheck, CheckBox surCheck,
                              CheckBox fatherCheck, CheckBox dobCheck,CheckBox nationalCheck,CheckBox genderCheck){
        if (idCheck.isChecked()){
            myRef.child("Student").child(id).child("id").removeValue();
        }
        if (nameCheck.isChecked()){
            myRef.child("Student").child(id).child("name").removeValue();
        }
        if (surCheck.isChecked()){
            myRef.child("Student").child(id).child("surname").removeValue();
        }
        if (fatherCheck.isChecked()){
            myRef.child("Student").child(id).child("fatherName").removeValue();
        }
        if (dobCheck.isChecked()){
            myRef.child("Student").child(id).child("dob").removeValue();
        }
        if (nationalCheck.isChecked()){
            myRef.child("Student").child(id).child("nationalID").removeValue();
        }
        if (genderCheck.isChecked()){
            myRef.child("Student").child(id).child("gender").removeValue();
        }
        Toasty.success(getBaseContext(), "Selected Student Information Deleted", Toast.LENGTH_SHORT, true).show();
    }
}