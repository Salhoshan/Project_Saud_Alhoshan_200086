package com.example.projectsaudalhoshan200086;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class updateSQL extends AppCompatActivity {
    final DatabaseHelper MyDB = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sql);
        CheckBox checkID = findViewById(R.id.idCheckbox);
        CheckBox checkName = findViewById(R.id.nameCheckbox);
        CheckBox checkSur = findViewById(R.id.surCheckbox);
        CheckBox checkFather = findViewById(R.id.fatherCheckbox);
        CheckBox checkDOB = findViewById(R.id.dobCheckbox);
        CheckBox checkNational = findViewById(R.id.nationalCheckbox);
        CheckBox checkGender = findViewById(R.id.genderCheckbox);

        EditText id = findViewById(R.id.updateID);

        EditText idInput = findViewById(R.id.inputIdUpdate);
        EditText name = findViewById(R.id.inputNameUpdate);
        EditText surname = findViewById(R.id.inputSurUpdate);
        EditText father = findViewById(R.id.inputFatherUpdate);
        Button dob = findViewById(R.id.inputDOBUpdate);
        EditText national = findViewById(R.id.inputNationalUpdate);
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

        dob.setOnClickListener(view -> new DatePickerDialog(updateSQL.this, d,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show());

        Button updateSqlBttn = findViewById(R.id.updateSQLbttn);

        updateSqlBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkName.isChecked()){
                    String idVal = id.getText().toString();
                    String nameVal = name.getText().toString();
                    if (idVal.matches("") || nameVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        MyDB.updateName(idVal, nameVal);
                        Toasty.success(getBaseContext(), "Student with ID "+idVal+" has updated name to "+nameVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
                if (checkSur.isChecked()){
                    String idVal = id.getText().toString();
                    String surVal = surname.getText().toString();
                    if (idVal.matches("") || surVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        MyDB.updateSur(idVal, surVal);
                        Toasty.success(getBaseContext(), "Student with ID "+idVal+" has updated surname to "+surVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
                if (checkFather.isChecked()){
                    String idVal = id.getText().toString();
                    String fatherVal = father.getText().toString();
                    if (idVal.matches("") || fatherVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        MyDB.updateFather(idVal, fatherVal);
                        Toasty.success(getBaseContext(), "Student with ID "+idVal+" has updated father name to "+fatherVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
                if (checkDOB.isChecked()){
                    String idVal = id.getText().toString();
                    String dobVal = fmtDate.format(calendar.getTime());
                    if (idVal.matches("") || dobVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        MyDB.updateDOB(idVal, dobVal);
                        Toasty.success(getBaseContext(), "Student with ID "+idVal+" has updated DOB to "+dobVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
                if (checkNational.isChecked()){
                    String idVal = id.getText().toString();
                    String natVal = national.getText().toString();
                    if (idVal.matches("") || natVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        MyDB.updateNational(idVal, natVal);
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
                        MyDB.updateGender(idVal, genderVal);
                        Toasty.success(getBaseContext(), "Student with ID "+idVal+" has updated gender to "+genderVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
                if (checkID.isChecked()){
                    String idVal = id.getText().toString();
                    String idInputVal = idInput.getText().toString();
                    if (idVal.matches("") || idInputVal.matches("")) {
                        Toasty.error(getBaseContext(), "Please Input all fields to update Student", Toast.LENGTH_SHORT, true).show();
                    }else {
                        MyDB.updateID(idVal, idInputVal);
                        Toasty.success(getBaseContext(), "Student ID "+idVal+" has been updated to "+idInputVal, Toast.LENGTH_SHORT, true).show();
                    }
                }
            }
        });
    }
}