package com.ymerp.app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private ImageView imgUserPhoto;
    private View userStatus;
    private TextView tvUserName, tvUserId, tvSubscriber, tvBranch, tvUnit, tvFiscalYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_card);

        // ربط عناصر البطاقة
        imgUserPhoto = findViewById(R.id.imgUserPhoto);
        userStatus = findViewById(R.id.userStatus);
        tvUserName = findViewById(R.id.tvUserName);
        tvUserId = findViewById(R.id.tvUserId);
        tvSubscriber = findViewById(R.id.tvSubscriber);
        tvBranch = findViewById(R.id.tvBranch);
        tvUnit = findViewById(R.id.tvUnit);
        tvFiscalYear = findViewById(R.id.tvFiscalYear);

        // تحميل بيانات المستخدم (مثال)
        tvUserName.setText("مدين الحكيمي / Madyan AL-hakimi");
        tvUserId.setText("رقم المستخدم: 1001");
        tvSubscriber.setText("رقم المشترك: 2001");
        tvBranch.setText("رقم الفرع: 3001");
        tvUnit.setText("الوحدة المحاسبية: 4001");
        tvFiscalYear.setText("السنة المالية: 2026");

        // تحديث الحالة عند تسجيل الدخول
        setUserStatus("active");
    }

    // دالة لتغيير حالة المستخدم
    private void setUserStatus(String status) {
        switch (status) {
            case "active":
                userStatus.setBackgroundResource(R.drawable.status_indicator_green);
                break;
            case "busy":
                userStatus.setBackgroundResource(R.drawable.status_indicator_yellow);
                break;
            case "offline":
                userStatus.setBackgroundResource(R.drawable.status_indicator_red);
                break;
        }
    }
}
