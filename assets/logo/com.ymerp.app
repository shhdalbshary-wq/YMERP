package com.ymerp.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etFiscalYear, etSubscriber, etUnit, etBranch;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // شاشة تسجيل الدخول

        // ربط الحقول
        etUsername   = findViewById(R.id.etUsername);
        etPassword   = findViewById(R.id.etPassword);
        etFiscalYear = findViewById(R.id.etFiscalYear);
        etSubscriber = findViewById(R.id.etSubscriberNumber);
        etUnit       = findViewById(R.id.etUnitNumber);
        etBranch     = findViewById(R.id.etBranchNumber);
        btnLogin     = findViewById(R.id.btnLogin);

        // زر تسجيل الدخول
        btnLogin.setOnClickListener(v -> {
            String username   = etUsername.getText().toString();
            String password   = etPassword.getText().toString();
            String fiscalYear = etFiscalYear.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "الرجاء إدخال اسم المستخدم وكلمة المرور", Toast.LENGTH_SHORT).show();
            } else {
                // ✅ تحقق من البيانات (مثال مبسط)
                if (username.equals("admin") && password.equals("1234")) {
                    Toast.makeText(LoginActivity.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();

                    // تمرير البيانات إلى بطاقة المستخدم
                    Intent intent = new Intent(LoginActivity.this, UserCardActivity.class);
                    intent.putExtra("USERNAME", username);
                    intent.putExtra("FISCAL_YEAR", fiscalYear);
                    intent.putExtra("SUBSCRIBER", etSubscriber.getText().toString());
                    intent.putExtra("UNIT", etUnit.getText().toString());
                    intent.putExtra("BRANCH", etBranch.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "بيانات الدخول غير صحيحة", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
