package com.example.projectsaudalhoshan200086;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class editInfo extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://projectapp-ca005-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        CheckBox checkName = findViewById(R.id.nameCheckbox2);
        CheckBox checkSur = findViewById(R.id.surCheckbox2);
        CheckBox checkFather = findViewById(R.id.fatherCheckbox2);
        CheckBox checkDOB = findViewById(R.id.dobCheckbox2);
        CheckBox checkNational = findViewById(R.id.nationalCheckbox2);
        CheckBox checkGender = findViewById(R.id.genderCheckbox2);

        EditText id = findViewById(R.id.inputID2);

        EditText name = findViewById(R.id.inputNameUpdate2);
        EditText surname = findViewById(R.id.inputSurUpdate2);
        EditText father = findViewById(R.id.inputFatherUpdate2);
        Button dob = findViewById(R.id.inputDOBUpdate2);
        EditText national = findViewById(R.id.inputNationalUpdate2);
        RadioGroup group = findViewById(R.id.rdGroup);
        RadioButton male = findViewById(R.id.maleUpdate);
        RadioButton female = findViewById(R.id.femaleUpdate);

        Calendar calendar = Calendar.getInstance();
        DateFormat fmtDate = DateFormat.getDateInstance();
        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        };

        dob.setOnClickListener(view -> new DatePickerDialog(editInfo.this, d,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show());

        Button updateSqlBttn = findViewById(R.id.updateSQLbttn2);

        updateSqlBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkName.isChecked()){
                    String idVal = id.getText().toString();
                    String nameVal = name.getText().toString();
                    if (idVal.matches("") || nameVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        updateName(idVal, nameVal);
                        Toasty.success(getBaseContext(), "Student with ID "+idVal+" has updated name to "+nameVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
                if (checkSur.isChecked()){
                    String idVal = id.getText().toString();
                    String surVal = surname.getText().toString();
                    if (idVal.matches("") || surVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        updateSurname(idVal, surVal);
                        Toasty.success(getBaseContext(), "Student with ID "+idVal+" has updated surname to "+surVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
                if (checkFather.isChecked()){
                    String idVal = id.getText().toString();
                    String fatherVal = father.getText().toString();
                    if (idVal.matches("") || fatherVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        updateFather(idVal, fatherVal);
                        Toasty.success(getBaseContext(), "Student with ID "+idVal+" has updated father name to "+fatherVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
                if (checkDOB.isChecked()){
                    String idVal = id.getText().toString();
                    String dobVal = fmtDate.format(calendar.getTime());
                    if (idVal.matches("") || dobVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        updateDOB(idVal, dobVal);
                        Toasty.success(getBaseContext(), "Student with ID "+idVal+" has updated DOB to "+dobVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
                if (checkNational.isChecked()){
                    String idVal = id.getText().toString();
                    String natVal = national.getText().toString();
                    if (idVal.matches("") || natVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        updateNational(idVal, natVal);
                        Toasty.success(getBaseContext(), "Student with ID "+idVal+" has updated National ID to "+natVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
                if (checkGender.isChecked()){
                    String idVal = id.getText().toString();
                    String genderVal = "";
                    if (male.isChecked()){
                        genderVal = "Male";
                    }
                    if (female.isChecked()){
                        genderVal = "Female";
                    }
                    if (idVal.matches("") || genderVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        updateGender(idVal, genderVal);
                        Toasty.success(getBaseContext(), "Student with ID "+idVal+" has updated gender to "+genderVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
            }
        });
    }
    public void updateName(String id, String name){
        myRef.child("Student").child(id).child("name").setValue(name);
        Toasty.success(getBaseContext(), "Student Name Updated to "+name, Toast.LENGTH_SHORT, true).show();
    }
    public void updateSurname(String id, String surname){
        myRef.child("Student").child(id).child("surname").setValue(surname);
        Toasty.success(getBaseContext(), "Student Surname Updated to "+surname, Toast.LENGTH_SHORT, true).show();
    }
    public void updateFather(String id, String father){
        myRef.child("Student").child(id).child("fatherName").setValue(father);
        Toasty.success(getBaseContext(), "Student Father's name Updated to "+father, Toast.LENGTH_SHORT, true).show();
    }
    public void updateDOB(String id, String dob){
        myRef.child("Student").child(id).child("dob").setValue(dob);
        Toasty.success(getBaseContext(), "Student DOB Updated to "+dob, Toast.LENGTH_SHORT, true).show();
    }
    public void updateNational(String id, String national){
        myRef.child("Student").child(id).child("nationalID").setValue(national);
        Toasty.success(getBaseContext(), "Student National ID Updated to "+national, Toast.LENGTH_SHORT, true).show();
    }
    public void updateGender(String id, String gender){
        myRef.child("Student").child(id).child("gender").setValue(gender);
        Toasty.success(getBaseContext(), "Student Gender Updated to "+gender, Toast.LENGTH_SHORT, true).show();
    }
}