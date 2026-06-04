package com.ymerp.app;

import java.io.Serializable;

/**
 * فئة المستخدم
 * User - تمثل بيانات المستخدم
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String passwordHash;
    private String fullName;
    private String email;
    private int userNumber;
    private int subscriberNumber;
    private int branchNumber;
    private int unitNumber;
    private String role;
    private boolean active;
    private long createdAt;
    private long lastLogin;

    /**
     * Constructor كامل للمستخدم
     */
    public User(int id, String username, String passwordHash, String fullName,
                String email, int userNumber, int subscriberNumber, 
                int branchNumber, int unitNumber, String role) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.email = email;
        this.userNumber = userNumber;
        this.subscriberNumber = subscriberNumber;
        this.branchNumber = branchNumber;
        this.unitNumber = unitNumber;
        this.role = role;
        this.active = true;
        this.createdAt = System.currentTimeMillis();
        this.lastLogin = 0;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public int getSubscriberNumber() {
        return subscriberNumber;
    }

    public int getBranchNumber() {
        return branchNumber;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    // Setters
    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                '}';
    }
}
