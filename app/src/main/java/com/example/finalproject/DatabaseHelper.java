package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "SaveIt.db";
    final static int DATABASE_VERSION = 6;

    final static String RESTAURANT = "Restaurant_table";
    final static String R_COL1 = "RID";
    final static String R_COL2 = "Name";
    final static String R_COL3 = "Start_Time";
    final static String R_COL4 = "End_Time";
    final static String R_COL5 = "Street_Num";
    final static String R_COL6 = "Street";
    final static String R_COL7 = "City";
    final static String R_COL8 = "Postal_Code";

    final static String BUNDLES = "Bundles_table";
    final static String B_COL1 = "BID";
    final static String B_COL2 = "Price";
    final static String B_COL3 = "Items";
    final static String B_COL4 = "RID";

    final static String USER = "User_table";
    final static String U_COL1 = "Email";
    final static String U_COL2 = "Name";
    final static String U_COL3 = "Password";
    final static String U_COL4 = "Phone_Num";
    final static String U_COL5 = "Street_Num";
    final static String U_COL6 = "Street";
    final static String U_COL7 = "City";
    final static String U_COL8 = "Postal_Code";

    final static String MANAGER = "Manager_table";
    final static String M_COL1 = "Email";
    final static String M_COL2 = "Name";
    final static String M_COL3 = "Password";
    final static String M_COL4 = "Phone_Num";
    final static String M_COL5 = "RID";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + RESTAURANT + " (" +
                R_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                R_COL2 + " TEXT, " +
                R_COL3 + " TIME, " +
                R_COL4 + " TIME, " +
                R_COL5 + " INTEGER, " +
                R_COL6 + " TEXT, " +
                R_COL7 + " TEXT, " +
                R_COL8 + " TEXT);";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + BUNDLES + " (" +
                B_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                B_COL2 + " INTEGER, " +
                B_COL3 + " TEXT, " +
                B_COL4 + " INTEGER, " +
                "FOREIGN KEY(" + B_COL4 + ") REFERENCES " + RESTAURANT + "(" + R_COL1 + ")" +
                ");";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + USER + " (" +
                U_COL1 + " TEXT PRIMARY KEY, " +
                U_COL2 + " TEXT, " +
                U_COL3 + " TEXT, " +
                U_COL4 + " INTEGER, " +
                U_COL5 + " INTEGER, " +
                U_COL6 + " TEXT, " +
                U_COL7 + " TEXT, " +
                U_COL8 + " TEXT" +
                ");";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + MANAGER + " (" +
                M_COL1 + " TEXT PRIMARY KEY," +
                M_COL2 + " TEXT," +
                M_COL3 + " TEXT," +
                M_COL4 + " TEXT," +
                M_COL5 + " INTEGER," +
                "FOREIGN KEY(" + M_COL5 + ") REFERENCES " + RESTAURANT + "(" + R_COL1 + ")" +
                ")";
        sqLiteDatabase.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RESTAURANT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BUNDLES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MANAGER);
        onCreate(sqLiteDatabase);

    }

    public boolean addBundleData(Integer price, String items)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(B_COL2, price);
        String[] itemArray = items.split(",");
        // Join the array of items into a comma-separated string
        String itemString = TextUtils.join(" ", itemArray);
        values.put(B_COL3, itemString);

        long l = sqLiteDatabase.insert(BUNDLES, null, values);
        if(l > 0)
            return true;
        else
            return false;
    }

}
