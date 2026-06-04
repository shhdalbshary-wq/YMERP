package com.ymerp.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * مساعد قاعدة البيانات
 * DatabaseHelper - يدير عمليات قاعدة البيانات SQLite
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

    // معلومات قاعدة البيانات
    private static final String DATABASE_NAME = "ymerp.db";
    private static final int DATABASE_VERSION = 2;

    // جداول قاعدة البيانات
    private static final String TABLE_USERS = "users";
    private static final String TABLE_SUMMARY = "summary";

    // أعمدة جدول المستخدمين
    public static final String COL_USER_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD_HASH = "password_hash";
    public static final String COL_FULL_NAME = "full_name";
    public static final String COL_EMAIL = "email";
    public static final String COL_USER_NUMBER = "user_number";
    public static final String COL_SUBSCRIBER_NUMBER = "subscriber_number";
    public static final String COL_BRANCH_NUMBER = "branch_number";
    public static final String COL_UNIT_NUMBER = "unit_number";
    public static final String COL_ROLE = "role";
    public static final String COL_ACTIVE = "active";
    public static final String COL_CREATED_AT = "created_at";
    public static final String COL_LAST_LOGIN = "last_login";

    // أعمدة جدول الملخص
    public static final String COL_SUMMARY_ID = "id";
    public static final String COL_INVOICES = "invoices";
    public static final String COL_CLIENTS = "clients";
    public static final String COL_SALES = "sales";
    public static final String COL_EXPENSES = "expenses";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        logger.info("DatabaseHelper initialized");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            logger.info("Creating database tables");

            // إنشاء جدول المستخدمين
            String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                    COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_USERNAME + " TEXT UNIQUE NOT NULL, " +
                    COL_PASSWORD_HASH + " TEXT NOT NULL, " +
                    COL_FULL_NAME + " TEXT NOT NULL, " +
                    COL_EMAIL + " TEXT, " +
                    COL_USER_NUMBER + " INTEGER, " +
                    COL_SUBSCRIBER_NUMBER + " INTEGER, " +
                    COL_BRANCH_NUMBER + " INTEGER, " +
                    COL_UNIT_NUMBER + " INTEGER, " +
                    COL_ROLE + " TEXT DEFAULT 'user', " +
                    COL_ACTIVE + " INTEGER DEFAULT 1, " +
                    COL_CREATED_AT + " LONG, " +
                    COL_LAST_LOGIN + " LONG)";

            db.execSQL(createUsersTable);
            logger.debug("Users table created successfully");

            // إنشاء جدول الملخص
            String createSummaryTable = "CREATE TABLE " + TABLE_SUMMARY + " (" +
                    COL_SUMMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_INVOICES + " REAL DEFAULT 0, " +
                    COL_CLIENTS + " INTEGER DEFAULT 0, " +
                    COL_SALES + " REAL DEFAULT 0, " +
                    COL_EXPENSES + " REAL DEFAULT 0)";

            db.execSQL(createSummaryTable);
            logger.debug("Summary table created successfully");

            // إدراج بيانات تجريبية
            insertDefaultData(db);

        } catch (Exception e) {
            logger.error("Error creating database tables", e);
            throw new RuntimeException("فشل إنشاء جداول قاعدة البيانات", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            logger.warn("Upgrading database from version {} to {}", oldVersion, newVersion);

            // حذف الجداول القديمة
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUMMARY);

            // إعادة إنشاء الجداول
            onCreate(db);

        } catch (Exception e) {
            logger.error("Error upgrading database", e);
            throw new RuntimeException("فشل ترقية قاعدة البيانات", e);
        }
    }

    /**
     * إدراج البيانات الافتراضية
     * 
     * @param db قاعدة البيانات
     */
    private void insertDefaultData(SQLiteDatabase db) {
        try {
            // إدراج بيانات تجريبية للملخص
            ContentValues summaryValues = new ContentValues();
            summaryValues.put(COL_INVOICES, 85300.0);
            summaryValues.put(COL_CLIENTS, 1250);
            summaryValues.put(COL_SALES, 320750.0);
            summaryValues.put(COL_EXPENSES, 95200.0);

            db.insert(TABLE_SUMMARY, null, summaryValues);
            logger.info("Default summary data inserted");

        } catch (Exception e) {
            logger.warn("Error inserting default data", e);
        }
    }

    /**
     * البحث عن مستخدم باستخدام اسم المستخدم
     * 
     * @param username اسم المستخدم
     * @return كائن المستخدم أو null
     */
    public User getUserByUsername(String username) {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = this.getReadableDatabase();
            String[] selectionArgs = {username};

            cursor = db.query(
                    TABLE_USERS,
                    null,
                    COL_USERNAME + " = ?",
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                return createUserFromCursor(cursor);
            }

        } catch (Exception e) {
            logger.error("Error retrieving user by username: {}", username, e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return null;
    }

    /**
     * جلب بيانات الملخص
     * 
     * @return كائن SummaryData
     */
    public SummaryData getSummaryData() {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = this.getReadableDatabase();
            cursor = db.query(TABLE_SUMMARY, null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                double invoices = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_INVOICES));
                int clients = cursor.getInt(cursor.getColumnIndexOrThrow(COL_CLIENTS));
                double sales = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_SALES));
                double expenses = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_EXPENSES));

                return new SummaryData(invoices, clients, sales, expenses);
            }

        } catch (Exception e) {
            logger.error("Error retrieving summary data", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return null;
    }

    /**
     * إنشاء كائن User من بيانات Cursor
     * 
     * @param cursor مؤشر البيانات
     * @return كائن User
     */
    private User createUserFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_USER_ID));
        String username = cursor.getString(cursor.getColumnIndexOrThrow(COL_USERNAME));
        String passwordHash = cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD_HASH));
        String fullName = cursor.getString(cursor.getColumnIndexOrThrow(COL_FULL_NAME));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL));
        int userNumber = cursor.getInt(cursor.getColumnIndexOrThrow(COL_USER_NUMBER));
        int subscriberNumber = cursor.getInt(cursor.getColumnIndexOrThrow(COL_SUBSCRIBER_NUMBER));
        int branchNumber = cursor.getInt(cursor.getColumnIndexOrThrow(COL_BRANCH_NUMBER));
        int unitNumber = cursor.getInt(cursor.getColumnIndexOrThrow(COL_UNIT_NUMBER));
        String role = cursor.getString(cursor.getColumnIndexOrThrow(COL_ROLE));

        return new User(id, username, passwordHash, fullName, email, 
                userNumber, subscriberNumber, branchNumber, unitNumber, role);
    }
}
