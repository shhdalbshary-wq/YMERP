private boolean checkLogin(String username, String password) {
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?",
            new String[]{username, password});

    boolean result = cursor.moveToFirst();
    cursor.close();
    db.close();
    return result;
}

btnLogin.setOnClickListener(v -> {
    String username = etUsername.getText().toString();
    String password = etPassword.getText().toString();

    if (checkLogin(username, password)) {
        Toast.makeText(LoginActivity.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();

        // تمرير البيانات إلى لوحة التحكم
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
        finish();
    } else {
        Toast.makeText(LoginActivity.this, "بيانات الدخول غير صحيحة", Toast.LENGTH_SHORT).show();
    }
});
