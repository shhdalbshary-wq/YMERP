package com.ymerp.app;

import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * خدمة المصادقة والأمان
 * UserAuthenticationService - توفر آليات آمنة للمصادقة
 */
public class UserAuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationService.class);
    
    private final Context context;
    private final PasswordEncoder passwordEncoder;
    private final DatabaseHelper databaseHelper;

    public UserAuthenticationService(Context context) {
        this.context = context;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.databaseHelper = new DatabaseHelper(context);
    }

    /**
     * مصادقة المستخدم بشكل آمن
     * 
     * @param username اسم المستخدم
     * @param password كلمة المرور
     * @return جلسة المستخدم إذا كانت بيانات صحيحة، وإلا null
     */
    public UserSession authenticate(String username, String password) {
        try {
            logger.info("Attempting to authenticate user: {}", username);

            // البحث عن المستخدم في قاعدة البيانات
            User user = databaseHelper.getUserByUsername(username);

            if (user == null) {
                logger.warn("User not found: {}", username);
                return null;
            }

            // التحقق من كلمة المرور المشفرة
            if (!passwordEncoder.matches(password, user.getPasswordHash())) {
                logger.warn("Invalid password for user: {}", username);
                return null;
            }

            logger.info("User authenticated successfully: {}", username);

            // إنشاء جلسة جديدة
            UserSession session = new UserSession(user);
            return session;

        } catch (Exception e) {
            logger.error("Error during authentication process", e);
            return null;
        }
    }

    /**
     * تشفير كلمة المرور
     * 
     * @param password كلمة المرور النصية
     * @return كلمة المرور المشفرة
     */
    public String encryptPassword(String password) {
        try {
            return passwordEncoder.encode(password);
        } catch (Exception e) {
            logger.error("Error while encrypting password", e);
            throw new RuntimeException("فشل تشفير كلمة المرور", e);
        }
    }

    /**
     * التحقق من صحة جلسة المستخدم
     * 
     * @param session جلسة المستخدم
     * @return صحة الجلسة
     */
    public boolean isValidSession(UserSession session) {
        if (session == null) {
            return false;
        }

        if (session.isExpired()) {
            logger.warn("User session expired: {}", session.getUsername());
            return false;
        }

        return true;
    }

    /**
     * تسجيل خروج المستخدم
     * 
     * @param session جلسة المستخدم
     */
    public void logout(UserSession session) {
        if (session != null) {
            logger.info("User logged out: {}", session.getUsername());
            session.invalidate();
        }
    }
}
