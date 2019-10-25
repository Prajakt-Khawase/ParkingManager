package com.example.parkingmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteDatabase.db";
    // Creating Attributes for Manager Table.
    public static final String TABLE_NAME_MANAGER = "MANAGER";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_MOBILE = "MOBILE";
    public static final String COLUMN_PASSWORD = "PASWWORD";
    public static final String COLUMN_NAME = "NAME";

    //Slot Booking Table
    public static final String TABLE_NAME_BOOKING = "BOOKING";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_MANAGER + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " VARCHAR," + COLUMN_EMAIL + " VARCHAR," + COLUMN_MOBILE + " VARCHAR," +
                COLUMN_PASSWORD + " VARCHAR);");
    }

    public void insertRecord(String name, String email, String mobile, String password) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
