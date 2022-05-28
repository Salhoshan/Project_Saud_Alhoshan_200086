package com.example.projectsaudalhoshan200086;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Students.db";
    public static final String TABLE_NAME = "student";
    public static final String COLUMN_ID = "id";
    //I ADDED THIS INPUT ID TO FIND THE CORRECT POSITION OF LIST WHEN PRESSED
    public static final String COLUMN_INPUT_ID = "input_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_FATHER_NAME = "father_name";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_NATIONAL_ID = "national_id";
    public static final String COLUMN_GENDER = "gender";

    public DatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_NAME+ "(" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                        COLUMN_INPUT_ID +" TEXT NOT NULL,"+
                        COLUMN_NAME + " TEXT NOT NULL," +
                        COLUMN_SURNAME + " TEXT NOT NULL,"+
                        COLUMN_FATHER_NAME + " TEXT NOT NULL,"+
                        COLUMN_DOB + " TEXT NOT NULL,"+
                        COLUMN_NATIONAL_ID + " TEXT NOT NULL,"+
                        COLUMN_GENDER + " TEXT NOT NULL);"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    public Cursor ViewList()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return x;
    }
    //I ADDED A METHOD TO MAKE THINGS EASIER FOR MYSELF
    public Cursor ViewListDesc(int getID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT descr FROM "+ TABLE_NAME+ " WHERE id = "+getID, null);
        return x;
    }

    public void AddStudent(String id, String name ,String surname, String father, String dob, String national, String gender)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_INPUT_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SURNAME, surname);
        values.put(COLUMN_FATHER_NAME, father);
        values.put(COLUMN_DOB, dob);
        values.put(COLUMN_NATIONAL_ID, national);
        values.put(COLUMN_GENDER, gender);
        db.insert(TABLE_NAME, null, values);
    }
    public Integer DeleteStudent(String id) {
    SQLiteDatabase db = this.getWritableDatabase();
    return db.delete(TABLE_NAME, "input_id = ?", new String[]{id});
}
    public boolean updateID(String id, String id_input) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET input_id = "+"'"+id_input+"' "+ "WHERE input_id = "+"'"+id+"'");
        return true;
    }
    public boolean updateName(String id, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET name = "+"'"+name+"' "+ "WHERE input_id = "+"'"+id+"'");
        return true;
    }
    public boolean updateSur(String id, String sur) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET surname = "+"'"+sur+"' "+ "WHERE input_id = "+"'"+id+"'");
        return true;
    }
    public boolean updateFather(String id, String father) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET father_name = "+"'"+father+"' "+ "WHERE input_id = "+"'"+id+"'");
        return true;
    }
    public boolean updateDOB(String id, String dob_input) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET dob = "+"'"+dob_input+"' "+ "WHERE input_id = "+"'"+id+"'");
        return true;
    }
    public boolean updateNational(String id, String national) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET national_id = "+"'"+national+"' "+ "WHERE input_id = "+"'"+id+"'");
        return true;
    }
    public boolean updateGender(String id, String gen) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET gender = "+"'"+gen+"' "+ "WHERE input_id = "+"'"+id+"'");
        return true;
    }
//    UPDATE `student`
//    SET `id` = (SELECT COUNT(`id`) FROM student)
//    WHERE `input_id` = '123456';
}