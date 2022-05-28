package com.example.projectsaudalhoshan200086;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {
    TextView temperature, city, humidity, clouds;
    String weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q=berlin&APPID=cb28959a91d7662c4db5379518d04f01";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button add = findViewById(R.id.addStudent);
        Button edit = findViewById(R.id.edit);
        Button delete = findViewById(R.id.delete);
        Button findSpecific = findViewById(R.id.findSpecific);
        Button viewAll = findViewById(R.id.viewAll);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity2.this, addUser.class));
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, editInfo.class));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, deleteStudent.class));
            }
        });
        findSpecific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, findSpecific.class));
            }
        });
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, studentList.class));
            }
        });
        city = findViewById(R.id.city2);
        temperature = findViewById(R.id.temp2);
        humidity = findViewById(R.id.humidity2);
        clouds = findViewById(R.id.clouds2);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String citySP = sp.getString("city",null);
        citySP = citySP.substring(0, 1).toUpperCase() + citySP.substring(1);
        city.setText(citySP);
        weather(weatherWebserviceURL);
    }
    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String citySP = sp.getString("city",null);
        citySP = citySP.substring(0, 1).toUpperCase() + citySP.substring(1);
        city.setText(citySP);
        weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q="+citySP+"&APPID=cb28959a91d7662c4db5379518d04f01";
        weather(weatherWebserviceURL);
    }
    public void weather(String url) {
        JsonObjectRequest jsonObj =
                new JsonObjectRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Saud","Response Retrieved");
                        Log.d("Saud-JSON",response.toString());
                        try {
                            JSONObject jsonMain = response.getJSONObject("main");
                            JSONObject jsonClouds = response.getJSONObject("clouds");

                            double temp = jsonMain.getDouble("temp");
                            temp = temp - 273.15;
                            Log.d("Saud-temp=","temp="+String.valueOf(temp));
                            temperature.setText(String.valueOf(String.format("%.2f", temp))+" °C");

                            double hum = jsonMain.getDouble("humidity");
                            Log.d("Saud-humidity=","humidity="+String.valueOf(hum));
                            humidity.setText(String.valueOf(hum)+" %");

                            double cloud = jsonClouds.getDouble("all");
                            Log.d("Saud-clouds=","clouds="+String.valueOf(cloud));
                            clouds.setText(String.valueOf(cloud));

                        }catch (JSONException e){
                            e.printStackTrace();
                            Log.e("JSON Error",e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Saud","Error retrieving URL");
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }
}