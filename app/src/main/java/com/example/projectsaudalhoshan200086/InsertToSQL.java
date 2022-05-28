package com.example.projectsaudalhoshan200086;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class InsertToSQL extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://projectapp-ca005-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference();
    final DatabaseHelper MyDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_to_sql);
        EditText idInput = findViewById(R.id.inputSQL);
        Button insertSqlBttn = findViewById(R.id.insertSqlBttn);


        insertSqlBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idVal = idInput.getText().toString();
                if (idVal.matches("")) {
                    Toasty.error(getBaseContext(), "Please Input ID to insert Student", Toast.LENGTH_SHORT, true).show();
                }else {
                    getStudent(idVal);
                }
            }
        });
    }
    private void getStudent(String ID) {
        myRef = database.getReference().child("Student").child(ID);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String idRet = snapshot.child("id").getValue().toString();
                String nameRet = snapshot.child("name").getValue().toString();
                String surRet = snapshot.child("surname").getValue().toString();
                String fatherRet = snapshot.child("fatherName").getValue().toString();
                String dobRet = snapshot.child("dob").getValue().toString();
                String nationalRet = snapshot.child("nationalID").getValue().toString();
                String genderRet = snapshot.child("gender").getValue().toString();

                MyDB.AddStudent(idRet, nameRet, surRet,fatherRet,dobRet,nationalRet,genderRet);
                Toasty.success(getBaseContext(), "Student "+nameRet+" with id "+idRet+" added to database", Toast.LENGTH_SHORT, true).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toasty.error(getBaseContext(), "Failed to get data", Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}