package com.example.projectsaudalhoshan200086;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class deleteSQL extends AppCompatActivity {
    final DatabaseHelper MyDB = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_sql);
        EditText id = findViewById(R.id.inputIdDelete);
        Button delete = findViewById(R.id.deleteRecordSQL);

        CheckBox idCheck = findViewById(R.id.idCheck2);
        CheckBox nameCheck = findViewById(R.id.nameCheck2);
        CheckBox surCheck = findViewById(R.id.surCheck2);
        CheckBox fatherCheck = findViewById(R.id.fatherCheck2);
        CheckBox dobCheck = findViewById(R.id.dobCheck2);
        CheckBox nationalCheck = findViewById(R.id.nationalCheck2);
        CheckBox genderCheck = findViewById(R.id.genderCheck2);
        Button deleteCheck = findViewById(R.id.deleteCheck2);

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
        MyDB.DeleteStudent(id);
        Toasty.success(getBaseContext(), "Student Record with ID "+id+" has been deleted", Toast.LENGTH_SHORT, true).show();
    }
    public void deleteRecords(String id, CheckBox idCheck, CheckBox nameCheck, CheckBox surCheck,
                              CheckBox fatherCheck, CheckBox dobCheck,CheckBox nationalCheck,CheckBox genderCheck){
        if (idCheck.isChecked()){
            MyDB.updateID(id, null);
        }
        if (nameCheck.isChecked()){
            MyDB.updateName(id, null);
        }
        if (surCheck.isChecked()){
            MyDB.updateSur(id, null);
        }
        if (fatherCheck.isChecked()){
            MyDB.updateFather(id, null);
        }
        if (dobCheck.isChecked()){
            MyDB.updateDOB(id, null);
        }
        if (nationalCheck.isChecked()){
            MyDB.updateNational(id, null);
        }
        if (genderCheck.isChecked()){
            MyDB.updateGender(id, null);
        }
        Toasty.success(getBaseContext(), "Selected Student Information with ID "+id+" has been deleted", Toast.LENGTH_SHORT, true).show();
    }
}