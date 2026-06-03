package com.ymerp.app;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardSummary extends AppCompatActivity {

    private TextView tvInvoices, tvClients, tvSales, tvExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // ربط عناصر البطاقات
        tvInvoices = findViewById(R.id.tvInvoices);
        tvClients  = findViewById(R.id.tvClients);
        tvSales    = findViewById(R.id.tvSales);
        tvExpenses = findViewById(R.id.tvExpenses);

        // تحميل البيانات من قاعدة البيانات أو API
        loadSummaryData();
    }

    // دالة لجلب البيانات وتحديث البطاقات
    private void loadSummaryData() {
        // مثال تجريبي (يتم استبداله بقاعدة بيانات أو API)
        int invoicesValue = 85300;
        int clientsValue  = 1250;
        int salesValue    = 320750;
        int expensesValue = 95200;

        // تحديث البطاقات
        tvInvoices.setText(invoicesValue + " ر.ي");
        tvClients.setText(clientsValue + " عميل");
        tvSales.setText(salesValue + " ر.ي");
        tvExpenses.setText(expensesValue + " ر.ي");
    }
}
