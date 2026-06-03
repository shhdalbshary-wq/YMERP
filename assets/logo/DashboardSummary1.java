package com.ymerp.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ymerp.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_SUMMARY = "summary";
    public static final String COL_ID = "id";
    public static final String COL_INVOICES = "invoices";
    public static final String COL_CLIENTS = "clients";
    public static final String COL_SALES = "sales";
    public static final String COL_EXPENSES = "expenses";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_SUMMARY + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_INVOICES + " INTEGER, " +
                COL_CLIENTS + " INTEGER, " +
                COL_SALES + " INTEGER, " +
                COL_EXPENSES + " INTEGER)";
        db.execSQL(createTable);

        // إدخال بيانات تجريبية
        db.execSQL("INSERT INTO " + TABLE_SUMMARY +
                " (" + COL_INVOICES + ", " + COL_CLIENTS + ", " + COL_SALES + ", " + COL_EXPENSES + ") " +
                "VALUES (85300, 1250, 320750, 95200)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUMMARY);
        onCreate(db);
    }
}
