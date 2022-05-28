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

public class findSpecific extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://projectapp-ca005-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_specific);
        EditText idInput = findViewById(R.id.inputID4);
        Button findSp = findViewById(R.id.findStudent);
        TextView id = findViewById(R.id.idSp);
        TextView name = findViewById(R.id.NameSp);
        TextView sur = findViewById(R.id.surSp);
        TextView father = findViewById(R.id.fatherSp);
        TextView dob = findViewById(R.id.dobSp);
        TextView national = findViewById(R.id.nationalSp);
        TextView gender = findViewById(R.id.genderSp);

        TextView idInvis = findViewById(R.id.idInvis);
        TextView nameInvis = findViewById(R.id.nameInvis);
        TextView surnameInvis = findViewById(R.id.surnameInvis);
        TextView fatherInvis = findViewById(R.id.fatherInvis);
        TextView dobInvis = findViewById(R.id.dobInvis);
        TextView nationalInvis = findViewById(R.id.nationalInvis);
        TextView genderInvis = findViewById(R.id.genderInvis);

        findSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idVal = idInput.getText().toString();
                if (idVal.matches("")) {
                    Toasty.error(getBaseContext(), "Please Input ID to find Student", Toast.LENGTH_SHORT, true).show();
                }else {
                    getStudent(idVal, id, name, sur, father, dob, national, gender);
                    idInvis.setVisibility(View.VISIBLE);
                    nameInvis.setVisibility(View.VISIBLE);
                    surnameInvis.setVisibility(View.VISIBLE);
                    fatherInvis.setVisibility(View.VISIBLE);
                    dobInvis.setVisibility(View.VISIBLE);
                    nationalInvis.setVisibility(View.VISIBLE);
                    genderInvis.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void getStudent(String ID, TextView idText,TextView name, TextView surname,
                            TextView father, TextView dob, TextView national, TextView gender ) {
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

                idText.setText(idRet);
                name.setText(nameRet);
                surname.setText(surRet);
                father.setText(fatherRet);
                dob.setText(dobRet);
                national.setText(nationalRet);
                gender.setText(genderRet);
                Toasty.success(getBaseContext(), "Student Information Retrieved", Toast.LENGTH_SHORT, true).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toasty.error(getBaseContext(), "Failed to get data", Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}