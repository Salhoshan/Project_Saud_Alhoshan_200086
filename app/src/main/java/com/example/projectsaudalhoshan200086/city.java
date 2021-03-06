package com.example.projectsaudalhoshan200086;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class city extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        setContentView(R.layout.activity_city);
        EditText city = findViewById(R.id.cityInput);
        Button chooseCity = findViewById(R.id.chooseCity);

        chooseCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityVal = city.getText().toString();
                if (cityVal.matches("")) {
                    Toasty.error(getBaseContext(), "Please Input All Fields to Change City", Toast.LENGTH_SHORT, true).show();
                }else{
                    editor.clear();
                    editor.putString("city", cityVal);
                    editor.commit();
                    Toasty.success(getBaseContext(), "City Changed", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }
}