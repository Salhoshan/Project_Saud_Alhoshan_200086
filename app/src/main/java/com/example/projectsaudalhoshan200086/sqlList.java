package com.example.projectsaudalhoshan200086;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class sqlList extends ListActivity {
    DatabaseHelper MyDB;
    ArrayList<String> students = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_list);
        MyDB = new DatabaseHelper(this);
        Cursor cur = MyDB.ViewList();

        while(cur.moveToNext())
        {
            students.add("id: "+cur.getString(1)
                    +"\n"+("Name: "+cur.getString(2)
                    +"\n"+("Surname: "+cur.getString(3)
                    +"\n"+("Father's Name: "+cur.getString(4)
                    +"\n"+("Date of Birth: "+cur.getString(5)
                    +"\n"+("National ID: "+cur.getString(6)
                    +"\n"+("Gender: "+cur.getString(7)
            )))))));
        }
        //Toast.makeText(sqlList.this,"Success",Toast.LENGTH_SHORT).show();

        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_sql_list, R.id.msg, students));

    }

    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        String[] studentSelected = students.get(position).split("\n", 6);
        Toasty.info(getBaseContext(),studentSelected[1]+"\n"+studentSelected[2], Toast.LENGTH_SHORT, true).show();
    }
}