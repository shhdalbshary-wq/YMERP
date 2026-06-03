package com.ymerp.app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardSummary extends AppCompatActivity {

    private TextView tvInvoices, tvClients, tvSales, tvExpenses;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // ربط عناصر البطاقات
        tvInvoices = findViewById(R.id.tvInvoices);
        tvClients  = findViewById(R.id.tvClients);
        tvSales    = findViewById(R.id.tvSales);
        tvExpenses = findViewById(R.id.tvExpenses);

        dbHelper = new DatabaseHelper(this);

        // تحميل البيانات من قاعدة البيانات
        loadSummaryData();
    }

    private void loadSummaryData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_SUMMARY + " LIMIT 1", null);

        if (cursor.moveToFirst()) {
            int invoicesValue = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_INVOICES));
            int clientsValue  = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_CLIENTS));
            int salesValue    = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SALES));
            int expensesValue = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_EXPENSES));

            tvInvoices.setText(invoicesValue + " ر.ي");
            tvClients.setText(clientsValue + " عميل");
            tvSales.setText(salesValue + " ر.ي");
            tvExpenses.setText(expensesValue + " ر.ي");
        }
        cursor.close();
        db.close();
    }
}
