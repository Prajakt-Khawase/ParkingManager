package com.example.parkingmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public static final String TABLE_NAME_SLOT = "SLOTS";
    public static final String COLUMN_SLOT_NO = "NO";
    public static final String COLUMN_BOOKING_STATUS = "STATUS";
    public static final String COLUMN_SLOT_TYPE = "TYPE";

    //Booking table
    public static final String TABLE_NAME_BOOKING = "BOOKINGS";
    public static final String TABLE_SLOT_NO = "SLOTNO";
    public static final String TABLE_OWNER_NAME = "OWNER";
    public static final String TABLE_MOBILE_NO = "MOBILE_NO";
    public static final String TABLE_EMAIL = "EMAIL";
    public static final String TABLE_VEHICLE_NO = "VEHICLE_NO";
    public static final String TABLE_IN_DATE_TIME = "IN_DATE_TIME";
    public static final String TABLE_OUT_DATE_TIME = "OUT_DATE_TIME";
    public static final String TABLE_MANAGER_NAME = "MANAGER";
    public static final String TABLE_VEHICLE_TYPE = "SLOT_TYPE";
    public static final String TABLE_BOOKING_STATUS = "STATUS";
    public static final String TABLE_PARKING_CHARGE = "CHARGE";


    private SQLiteDatabase database;

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
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_MOBILE, mobile);
        contentValues.put(COLUMN_PASSWORD, password);
        database.insert(TABLE_NAME_MANAGER, null, contentValues);
        database.close();
    }

    //For slot table slot insert record
    public void insertSlotRecord(int no, boolean status, String type) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SLOT_NO, no);
        contentValues.put(COLUMN_BOOKING_STATUS, status);
        contentValues.put(COLUMN_SLOT_TYPE, type);
        database.insert(TABLE_NAME_SLOT, null, contentValues);
        database.close();
    }
    //    //for booking record
    public void insertBookingRecord(int slotno, String owner, String mobile, String email, String vehicle, String intime, String outtime, String manager, String type, boolean  status) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MANAGER);
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME_MANAGER, null );
        return res;
    }

    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(COLUMN_ID, "ID = ?",new String[] {id});
        db.close();
    }
    public Cursor managerLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] coulmnNames = new String[] {COLUMN_ID,COLUMN_NAME,COLUMN_EMAIL,COLUMN_MOBILE,COLUMN_PASSWORD};
        String whereClause = COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD  + " = ?";
        String[] params = new String[] {
                username,
                password
        };
        Cursor c = db.query(TABLE_NAME_MANAGER, coulmnNames, whereClause, params, null, null, null);

        return c;
    }

}
