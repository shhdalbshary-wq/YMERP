package com.ymerp.app;

import java.io.Serializable;
import java.util.Date;

/**
 * فئة جلسة المستخدم
 * UserSession - تمثل جلسة المستخدم المصرح به
 */
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final long SESSION_TIMEOUT_MS = 30 * 60 * 1000; // 30 دقيقة

    private final String sessionId;
    private final User user;
    private final Date createdAt;
    private Date lastActivityAt;
    private boolean valid;

    public UserSession(User user) {
        this.sessionId = generateSessionId();
        this.user = user;
        this.createdAt = new Date();
        this.lastActivityAt = new Date();
        this.valid = true;
    }

    /**
     * توليد معرف جلسة فريد
     * 
     * @return معرف الجلسة
     */
    private String generateSessionId() {
        return String.valueOf(System.nanoTime()) + "_" + 
               String.valueOf(Math.random()).substring(2);
    }

    /**
     * التحقق من انتهاء صلاحية الجلسة
     * 
     * @return صحة الجلسة
     */
    public boolean isExpired() {
        if (!valid) {
            return true;
        }

        long elapsedTime = new Date().getTime() - lastActivityAt.getTime();
        if (elapsedTime > SESSION_TIMEOUT_MS) {
            this.valid = false;
            return true;
        }

        return false;
    }

    /**
     * تحديث وقت النشاط الأخير
     */
    public void refreshActivity() {
        this.lastActivityAt = new Date();
    }

    /**
     * إلغاء الجلسة
     */
    public void invalidate() {
        this.valid = false;
    }

    // Getters
    public String getSessionId() {
        return sessionId;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getLastActivityAt() {
        return lastActivityAt;
    }

    public boolean isValid() {
        return valid;
    }
}
