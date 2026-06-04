@Override
public void onCreate(SQLiteDatabase db) {
    String createUsersTable = "CREATE TABLE users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT, " +
            "password TEXT, " +
            "fiscal_year TEXT, " +
            "subscriber TEXT, " +
            "unit TEXT, " +
            "branch TEXT)";
    db.execSQL(createUsersTable);

    // إدخال بيانات تجريبية
    db.execSQL("INSERT INTO users (username, password, fiscal_year, subscriber, unit, branch) " +
            "VALUES ('admin', '1234', '2026', '2001', '4001', '3001')");
}
