package com.ymerp.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * نشاط تسجيل الدخول
 * LoginActivity - يوفر واجهة للمستخدم لتسجيل الدخول بشكل آمن
 */
public class LoginActivity extends AppCompatActivity {

    private static final Logger logger = LoggerFactory.getLogger(LoginActivity.class);

    private EditText etUsername;
    private EditText etPassword;
    private EditText etFiscalYear;
    private EditText etSubscriber;
    private EditText etUnit;
    private EditText etBranch;
    private Button btnLogin;

    private UserAuthenticationService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logger.info("Initializing LoginActivity");
        
        // ربط عناصر الواجهة
        initializeViews();
        
        // تهيئة خدمة المصادقة
        authService = new UserAuthenticationService(this);

        // إعداد زر تسجيل الدخول
        setupLoginButton();
    }

    /**
     * تهيئة عناصر الواجهة
     */
    private void initializeViews() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etFiscalYear = findViewById(R.id.etFiscalYear);
        etSubscriber = findViewById(R.id.etSubscriberNumber);
        etUnit = findViewById(R.id.etUnitNumber);
        etBranch = findViewById(R.id.etBranchNumber);
        btnLogin = findViewById(R.id.btnLogin);
    }

    /**
     * إعداد معالج زر تسجيل الدخول
     */
    private void setupLoginButton() {
        btnLogin.setOnClickListener(v -> {
            logger.debug("Login button clicked");
            handleLoginAttempt();
        });
    }

    /**
     * معالجة محاولة تسجيل الدخول
     */
    private void handleLoginAttempt() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        String fiscalYear = etFiscalYear.getText().toString().trim();
        String subscriber = etSubscriber.getText().toString().trim();
        String unit = etUnit.getText().toString().trim();
        String branch = etBranch.getText().toString().trim();

        // التحقق من صحة الإدخال
        if (!validateInput(username, password)) {
            logger.warn("Invalid input provided for login");
            return;
        }

        try {
            // محاولة المصادقة
            UserSession session = authService.authenticate(username, password);
            
            if (session != null) {
                logger.info("User successfully authenticated: {}", username);
                showSuccessMessage("تم تسجيل الدخول بنجاح");
                
                // الانتقال إلى لوحة التحكم
                navigateToDashboard(session, fiscalYear, subscriber, unit, branch);
            } else {
                logger.warn("Authentication failed for user: {}", username);
                showErrorMessage("بيانات الدخول غير صحيحة");
            }
        } catch (Exception e) {
            logger.error("Error during authentication process", e);
            showErrorMessage("حدث خطأ أثناء تسجيل الدخول");
        }
    }

    /**
     * التحقق من صحة البيانات المدخلة
     * 
     * @param username اسم المستخدم
     * @param password كلمة المرور
     * @return صحة البيانات
     */
    private boolean validateInput(String username, String password) {
        if (username.isEmpty()) {
            showErrorMessage("الرجاء إدخال اسم المستخدم");
            return false;
        }

        if (password.isEmpty()) {
            showErrorMessage("الرجاء إدخال كلمة المرور");
            return false;
        }

        if (username.length() < 3) {
            showErrorMessage("اسم المستخدم يجب أن يكون 3 أحرف على الأقل");
            return false;
        }

        if (password.length() < 4) {
            showErrorMessage("كلمة المرور يجب أن تكون 4 أحرف على الأقل");
            return false;
        }

        return true;
    }

    /**
     * الانتقال إلى لوحة التحكم
     * 
     * @param session جلسة المستخدم
     * @param fiscalYear السنة المالية
     * @param subscriber رقم المشترك
     * @param unit رقم الوحدة
     * @param branch رقم الفرع
     */
    private void navigateToDashboard(UserSession session, String fiscalYear, 
                                     String subscriber, String unit, String branch) {
        Intent intent = new Intent(LoginActivity.this, DashboardSummary.class);
        intent.putExtra("user_session", session);
        intent.putExtra("fiscal_year", fiscalYear);
        intent.putExtra("subscriber", subscriber);
        intent.putExtra("unit", unit);
        intent.putExtra("branch", branch);
        startActivity(intent);
        finish();
    }

    /**
     * عرض رسالة نجاح
     * 
     * @param message الرسالة
     */
    private void showSuccessMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * عرض رسالة خطأ
     * 
     * @param message الرسالة
     */
    private void showErrorMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
