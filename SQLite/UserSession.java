package com.ymerp.app;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {

    private static final String PREF_NAME = "YMERPUserSession";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_FISCAL_YEAR = "fiscal_year";
    private static final String KEY_SUBSCRIBER = "subscriber";
    private static final String KEY_UNIT = "unit";
    private static final String KEY_BRANCH = "branch";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public UserSession(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    // حفظ بيانات المستخدم
    public void createLoginSession(String username, String fiscalYear, String subscriber, String unit, String branch) {
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_FISCAL_YEAR, fiscalYear);
        editor.putString(KEY_SUBSCRIBER, subscriber);
        editor.putString(KEY_UNIT, unit);
        editor.putString(KEY_BRANCH, branch);
        editor.apply();
    }

    // استرجاع اسم المستخدم
    public String getUsername() {
        return prefs.getString(KEY_USERNAME, null);
    }

    // استرجاع السنة المالية
    public String getFiscalYear() {
        return prefs.getString(KEY_FISCAL_YEAR, null);
    }

    // استرجاع رقم المشترك
    public String getSubscriber() {
        return prefs.getString(KEY_SUBSCRIBER, null);
    }

    // استرجاع الوحدة المحاسبية
    public String getUnit() {
        return prefs.getString(KEY_UNIT, null);
    }

    // استرجاع رقم الفرع
    public String getBranch() {
        return prefs.getString(KEY_BRANCH, null);
    }

    // تسجيل الخروج
    public void logout() {
        editor.clear();
        editor.apply();
    }
}
