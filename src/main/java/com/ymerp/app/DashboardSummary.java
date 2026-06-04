package com.ymerp.app;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * نشاط لوحة التحكم
 * DashboardSummary - يعرض ملخص البيانات المالية للمستخدم
 */
public class DashboardSummary extends AppCompatActivity {

    private static final Logger logger = LoggerFactory.getLogger(DashboardSummary.class);

    private TextView tvInvoices;
    private TextView tvClients;
    private TextView tvSales;
    private TextView tvExpenses;

    private DatabaseHelper databaseHelper;
    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        logger.info("Initializing DashboardSummary Activity");

        // استرجاع جلسة المستخدم
        userSession = (UserSession) getIntent().getSerializableExtra("user_session");

        if (!isUserSessionValid()) {
            logger.warn("User session is invalid");
            finish();
            return;
        }

        // تهيئة عناصر الواجهة
        initializeViews();

        // تهيئة مساعد قاعدة البيانات
        databaseHelper = new DatabaseHelper(this);

        // تحميل بيانات الملخص
        loadSummaryData();

        logger.info("DashboardSummary Activity initialized successfully");
    }

    /**
     * التحقق من صحة جلسة المستخدم
     * 
     * @return صحة الجلسة
     */
    private boolean isUserSessionValid() {
        if (userSession == null) {
            logger.error("User session is null");
            return false;
        }

        if (userSession.isExpired()) {
            logger.error("User session has expired");
            return false;
        }

        return true;
    }

    /**
     * تهيئة عناصر الواجهة
     */
    private void initializeViews() {
        tvInvoices = findViewById(R.id.tvInvoices);
        tvClients = findViewById(R.id.tvClients);
        tvSales = findViewById(R.id.tvSales);
        tvExpenses = findViewById(R.id.tvExpenses);
    }

    /**
     * تحميل بيانات الملخص من قاعدة البيانات
     */
    private void loadSummaryData() {
        try {
            logger.debug("Loading summary data from database");

            SummaryData summaryData = databaseHelper.getSummaryData();

            if (summaryData != null) {
                updateUI(summaryData);
                logger.info("Summary data loaded and displayed successfully");
            } else {
                logger.warn("No summary data found in database");
                showDefaultData();
            }

        } catch (Exception e) {
            logger.error("Error loading summary data", e);
            showDefaultData();
        }
    }

    /**
     * تحديث واجهة المستخدم ببيانات الملخص
     * 
     * @param summaryData بيانات الملخص
     */
    private void updateUI(SummaryData summaryData) {
        tvInvoices.setText(formatCurrency(summaryData.getInvoices()) + " ر.ي");
        tvClients.setText(summaryData.getClients() + " عميل");
        tvSales.setText(formatCurrency(summaryData.getSales()) + " ر.ي");
        tvExpenses.setText(formatCurrency(summaryData.getExpenses()) + " ر.ي");
    }

    /**
     * عرض البيانات الافتراضية
     */
    private void showDefaultData() {
        tvInvoices.setText("85,300 ر.ي");
        tvClients.setText("1,250 عميل");
        tvSales.setText("320,750 ر.ي");
        tvExpenses.setText("95,200 ر.ي");
    }

    /**
     * تنسيق القيمة النقدية
     * 
     * @param value القيمة
     * @return القيمة المنسقة
     */
    private String formatCurrency(double value) {
        return String.format("%,.0f", value);
    }

    @Override
    protected void onResume() {
        super.onResume();
        
        // تحديث نشاط الجلسة
        if (userSession != null) {
            userSession.refreshActivity();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logger.info("DashboardSummary Activity destroyed");
    }
}
