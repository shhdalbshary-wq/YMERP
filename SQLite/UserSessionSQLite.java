package com.ymerp.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserSessionSQLite {

    private DatabaseHelper dbHelper;

    public UserSessionSQLite(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // إنشاء جلسة جديدة
    public void createLoginSession(String username, String fiscalYear, String subscriber, String unit, String branch) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // مسح أي جلسة قديمة
        db.delete("session", null, null);

        // إدخال بيانات الجلسة الجديدة
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("fiscal_year", fiscalYear);
        values.put("subscriber", subscriber);
        values.put("unit", unit);
        values.put("branch", branch);

        db.insert("session", null, values);
        db.close();
    }

    // استرجاع بيانات الجلسة
    public Cursor getSession() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery("SELECT * FROM session LIMIT 1", null);
    }

    // تسجيل الخروج
    public void logout() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("session", null, null);
        db.close();
    }
}@Override
public void onCreate(SQLiteDatabase db) {
    // جدول المستخدمين
    db.execSQL("CREATE TABLE users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT, " +
            "password TEXT, " +
            "fiscal_year TEXT, " +
            "subscriber TEXT, " +
            "unit TEXT, " +
            "branch TEXT)");

    // جدول الجلسة
    db.execSQL("CREATE TABLE session (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT, " +
            "fiscal_year TEXT, " +
            "subscriber TEXT, " +
            "unit TEXT, " +
            "branch TEXT)");
}UserSessionSQLite session = new UserSessionSQLite(LoginActivity.this);

if (checkLogin(username, password)) {
    session.createLoginSession(username, fiscalYear, subscriber, unit, branch);

    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
    startActivity(intent);
    finish();
}UserSessionSQLite session = new UserSessionSQLite(DashboardActivity.this);
Cursor cursor = session.getSession();

if (cursor.moveToFirst()) {
    String username   = cursor.getString(cursor.getColumnIndexOrThrow("username"));
    String fiscalYear = cursor.getString(cursor.getColumnIndexOrThrow("fiscal_year"));
    String subscriber = cursor.getString(cursor.getColumnIndexOrThrow("subscriber"));
    String unit       = cursor.getString(cursor.getColumnIndexOrThrow("unit"));
    String branch     = cursor.getString(cursor.getColumnIndexOrThrow("branch"));

    tvUserName.setText(username);
    tvFiscalYear.setText("السنة المالية: " + fiscalYear);
}
cursor.close();
