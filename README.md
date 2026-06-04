# 🟦 YMERP – نظام إدارة الموارد المؤسسية

**نظام متكامل لإدارة الموارد المؤسسية يجمع بين تطبيق Android وويب ديناميكي**

---

## 📋 نظرة عامة

**YMERP** هو نظام إدارة موارد مؤسسية شامل يوفر:

- 🔐 **مصادقة آمنة** للمستخدمين مع تشفير كلمات المرور
- 📊 **لوحة تحكم** شاملة لعرض الملخصات المالية
- 💾 **قاعدة بيانات** محلية وآمنة
- 📱 **تطبيق موبايل** على نظام Android
- 🌐 **واجهة ويب** تفاعلية

---

## 🚀 المتطلبات

### النظام
- **Java 11** أو أحدث
- **Android SDK 31** أو أحدث
- **Maven 3.6+** أو **Gradle 7+**

### المكتبات الرئيسية
```xml
- AndroidX AppCompat 1.4.1
- Spring Security Crypto 5.7.1
- SLF4J / Logback 1.7.36
- JUnit 4.13.2
- GSON 2.8.9
```

---

## 📁 هيكل المشروع

```
YMERP/
├── src/
│   ├── main/
│   │   ├── java/com/ymerp/app/
│   │   │   ├── LoginActivity.java              # نشاط تسجيل الدخول
│   │   │   ├── DashboardSummary.java           # لوحة التحكم
│   │   │   ├── UserAuthenticationService.java  # خدمة المصادقة الآمنة
│   │   │   ├── DatabaseHelper.java             # إدارة قاعدة البيانات
│   │   │   ├── User.java                       # نموذج المستخدم
│   │   │   ├── UserSession.java                # إدارة الجلسات
│   │   │   └── SummaryData.java                # بيانات الملخص
│   │   └── res/
│   │       └── layout/
│   └── test/
│       └── java/com/ymerp/app/
│           └── LoginActivityTest.java
├── designs/                                    # تصاميم الواجهات
├── assets/                                     # الشعارات والأيقونات
├── docs/                                       # التوثيق
├── index.html                                  # صفحة الويب الرئيسية
├── style.css                                   # أنماط الويب
├── pom.xml                                     # ملف بناء Maven
└── .gitignore                                  # ملف التجاهل
```

---

## 🔧 التثبيت والإعداد

### 1. استنساخ المستودع
```bash
git clone https://github.com/shhdalbshary-wq/YMERP.git
cd YMERP
```

### 2. تثبيت المتطلبات
```bash
# استخدام Maven
mvn clean install

# أو استخدام Gradle
gradle build
```

### 3. إعداد Android Studio
- افتح المشروع في Android Studio
- انتظر تنزيل المتطلبات
- أنشئ محاكي Android أو وصل جهاز فعلي

### 4. تشغيل التطبيق
```bash
# استخدام Maven
mvn install -DskipTests android:deploy android:run

# أو من خلال Android Studio
# اضغط على Run ▶
```

---

## 🔐 المصادقة والأمان

### تشفير كلمات المرور
يستخدم التطبيق **BCrypt** لتشفير كلمات المرور بشكل آمن:

```java
// تشفير كلمة المرور
String hashedPassword = authService.encryptPassword("myPassword123");

// التحقق من كلمة المرور
boolean isValid = passwordEncoder.matches("myPassword123", hashedPassword);
```

### جلسات المستخدم
- **المدة الافتراضية:** 30 دقيقة
- **التحديث التلقائي:** عند كل نشاط
- **الإلغاء الآمن:** عند تسجيل الخروج

---

## 💾 قاعدة البيانات

### جدول المستخدمين (users)
```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    full_name TEXT NOT NULL,
    email TEXT,
    user_number INTEGER,
    subscriber_number INTEGER,
    branch_number INTEGER,
    unit_number INTEGER,
    role TEXT DEFAULT 'user',
    active INTEGER DEFAULT 1,
    created_at LONG,
    last_login LONG
);
```

### جدول الملخص (summary)
```sql
CREATE TABLE summary (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    invoices REAL DEFAULT 0,
    clients INTEGER DEFAULT 0,
    sales REAL DEFAULT 0,
    expenses REAL DEFAULT 0
);
```

---

## 📊 الواجهة والتصميم

### نقاط النهاية (API)
```
GET  /api/dashboard        - جلب بيانات لوحة التحكم
POST /api/login            - تسجيل الدخول
POST /api/logout           - تسجيل الخروج
GET  /api/summary          - جلب بيانات الملخص
GET  /api/user/profile     - جلب بيانات المستخدم
```

### الألوان والأنماط
```css
aللون الأساسي: #003366 (أزرق غامق)
لون النجاح: #27ae60 (أخضر)
لون التحذير: #f1c40f (أصفر)
لون الخطأ: #c0392b (أحمر)
```

---

## 🧪 الاختبارات

### تشغيل الاختبارات
```bash
# اختبارات Unit
mvn test

# اختبارات Integration
mvn verify

# اختبارات مع التقرير
mvn test jacoco:report
```

### مثال على الاختبار
```java
@Test
public void testPasswordEncryption() {
    String password = "testPassword123";
    String hashedPassword = passwordEncoder.encode(password);
    
    assertTrue(passwordEncoder.matches(password, hashedPassword));
}
```

---

## 📝 التوثيق الإضافية

### معايير الكود
- **تنسيق:** Google Java Style Guide
- **Naming:** camelCase للمتغيرات والدوال
- **Comments:** تعليقات بالعربية والإنجليزية
- **Logging:** استخدام SLF4J في جميع الفئات

### الممارسات الأمنية
- ✅ تشفير كلمات المرور باستخدام BCrypt
- ✅ إدارة جلسات آمنة مع timeout
- ✅ معالجة الأخطاء والاستثناءات
- ✅ Logging للأحداث الهامة
- ✅ معالجة الموارد (try-with-resources)

---

## 🤝 المساهمة

### خطوات المساهمة
1. Fork المشروع
2. أنشئ فرع للميزة الجديدة (`git checkout -b feature/AmazingFeature`)
3. Commit التغييرات (`git commit -m 'Add some AmazingFeature'`)
4. Push للفرع (`git push origin feature/AmazingFeature`)
5. فتح Pull Request

### معايير الكود
- كود منظم وقابل للقراءة
- اختبارات شاملة
- توثيق واضح
- معالجة الأخطاء الصحيحة

---

## 📋 قائمة المهام المستقبلية

- [ ] إضافة واجهة REST API كاملة
- [ ] تطبيق نظام الصلاحيات والأدوار
- [ ] إضافة الإشعارات والتنبيهات
- [ ] تطبيق التقارير المتقدمة
- [ ] إضافة المزامنة السحابية
- [ ] تطبيق الاختبارات الشاملة
- [ ] توثيق API الكامل

---

## 📄 الترخيص

هذا المشروع مرخص تحت **Creative Commons Zero v1.0 Universal**

جميع الحقوق محفوظة © 2026 YMERP

---

## 📞 التواصل والدعم

**المالك:** shhdalbshary-wq  
**البريد الإلكتروني:** shhdalbshary@gmail.com  
**GitHub:** [shhdalbshary-wq](https://github.com/shhdalbshary-wq)

---

## 🎯 الشكر والتقدير

شكراً لك على استخدام YMERP!  
إذا واجهت أي مشاكل أو لديك اقتراحات، فضلاً افتح [Issue جديد](https://github.com/shhdalbshary-wq/YMERP/issues).

---

**آخر تحديث:** 4 يونيو 2026
