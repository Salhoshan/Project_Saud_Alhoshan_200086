package com.example.projectsaudalhoshan200086;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class addUser extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://projectapp-ca005-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        EditText id = findViewById(R.id.inputID);
        EditText name = findViewById(R.id.inputName);
        EditText surname = findViewById(R.id.inputSurname);
        EditText father = findViewById(R.id.inputFather);
        EditText nationalID = findViewById(R.id.inputNational);
        Button dateButton = findViewById(R.id.dateButton);
        RadioButton male = findViewById(R.id.maleUpdate);
        RadioButton female = findViewById(R.id.femaleUpdate);
        Button insert = findViewById(R.id.insert);

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

        dateButton.setOnClickListener(view -> new DatePickerDialog(addUser.this, d,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show());

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idVal = id.getText().toString();
                String nameVal = name.getText().toString();
                String surnameVal = surname.getText().toString();
                String fatherVal = father.getText().toString();
                String nIdVal = nationalID.getText().toString();
                String dob = fmtDate.format(calendar.getTime());
                String genderVal;
                if (idVal.matches("") || nameVal.matches("") || surnameVal.matches("") || fatherVal.matches("")
                        || nIdVal.matches("") || dob.matches("") || male.isChecked()==false && female.isChecked()==false) {
                    Toasty.error(getBaseContext(), "Please Input All Fields", Toast.LENGTH_SHORT, true).show();
                }else{
                    if (male.isChecked()){genderVal="Male";}
                    else{genderVal="Female";}
                    writeNewStudent(idVal, nameVal, surnameVal, fatherVal,nIdVal,dob,genderVal);
                    Toasty.success(getBaseContext(), "Student Inserted", Toast.LENGTH_SHORT, true).show();
                }
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void writeNewStudent(String id,
                              String name,
                              String surname,
                              String fatherName,
                              String nationalID,
                              String dob,
                              String gender){
        Student student = new Student(id, name, surname, fatherName, nationalID, dob, gender);

        myRef.child("Student").child(id).setValue(student);
    }
    @IgnoreExtraProperties
    public class Student{
        public String id;
        public String name;
        public String surname;
        public String fatherName;
        public String nationalID;
        public String dob;
        public String gender;
        public Student(){

        }
        public Student(String id, String name, String surname, String fatherName, String nationalID, String dob, String gender){
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.fatherName = fatherName;
            this.nationalID = nationalID;
            this.dob = dob;
            this.gender = gender;
        }
    }

}