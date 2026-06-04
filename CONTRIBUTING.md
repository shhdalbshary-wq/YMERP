# 🤝 دليل المساهمة

شكراً لاهتمامك بالمساهمة في مشروع YMERP! 

---

## 📋 قبل البدء

### تحقق من الآتي:
- [ ] قراءة [سياسة الأمان](SECURITY.md)
- [ ] فهم [معايير الكود](CODE_STYLE.md)
- [ ] التأكد من عدم وجود Issue مشابه
- [ ] تثبيت أدوات التطوير المطلوبة

---

## 🚀 خطوات المساهمة

### 1. Fork المشروع
```bash
# انتقل إلى GitHub واضغط زر Fork
```

### 2. استنسخ الفرع الخاص بك
```bash
git clone https://github.com/YOUR_USERNAME/YMERP.git
cd YMERP
```

### 3. أنشئ فرع جديد
```bash
git checkout -b feature/description-of-feature
# أو للإصلاح:
git checkout -b fix/description-of-fix
```

**قواعس تسمية الفروع:**
- `feature/` - ميزة جديدة
- `fix/` - إصلاح خطأ
- `docs/` - تحديثات التوثيق
- `refactor/` - إعادة هيكلة
- `test/` - إضافة اختبارات

### 4. اعمل على التغييرات
```bash
# قم بالتعديلات المطلوبة
# اختبر التغييرات محلياً
mvn clean install
mvn test
```

### 5. Commit التغييرات
```bash
git add .
git commit -m "feat: إضافة ميزة جديدة"
# أو
git commit -m "fix: إصلاح الخطأ في المصادقة"
```

**معايير رسائل الـ Commit:**
- استخدم الأمر الفعلي: `feat:`, `fix:`, `docs:`, `test:`, `refactor:`
- اكتب بالعربية أو الإنجليزية بوضوح
- كن موجزاً (أقل من 50 حرف للسطر الأول)

### 6. Push التغييرات
```bash
git push origin feature/description-of-feature
```

### 7. فتح Pull Request
1. انتقل إلى GitHub
2. اضغط `Compare & pull request`
3. املأ نموذج PR
4. اطلب مراجعة

---

## 📝 نموذج Pull Request

```markdown
## الوصف
وصف مختصر للتغييرات.

## النوع
- [ ] ميزة جديدة
- [ ] إصلاح خطأ
- [ ] تحسين الأداء
- [ ] تحديثات التوثيق

## المشكلة المرتبطة
يغلق #123

## التغييرات الرئيسية
- التغيير الأول
- التغيير الثاني

## اختبرت؟
- [ ] اختبارات Unit
- [ ] اختبارات Integration
- [ ] اختبار يدوي

## الصور / الفيديو
(إن وجد)

## قائمة التحقق
- [ ] تم اتباع معايير الكود
- [ ] تم إضافة الاختبارات
- [ ] تم تحديث التوثيق
- [ ] لا توجد أخطاء Linting
```

---

## 🧪 معايير الاختبار

### تغطية الاختبارات
- **الحد الأدنى:** 80% تغطية للكود الجديد
- **الهدف:** 90%+ تغطية

### الاختبارات المطلوبة
```bash
# اختبارات Unit
mvn test

# اختبارات Integration
mvn verify

# تقرير التغطية
mvn jacoco:report
```

### مثال اختبار جيد
```java
@Test
public void shouldAuthenticateValidUser() {
    // Arrange
    String username = "testuser";
    String password = "validPassword123";
    
    // Act
    UserSession session = authService.authenticate(username, password);
    
    // Assert
    assertNotNull("Session should not be null", session);
    assertTrue("Session should be valid", session.isValid());
}
```

---

## 📝 معايير الكود

### تنسيق الكود
```java
// ✅ صحيح
public void validateInput(String username, String password) {
    if (username.isEmpty()) {
        throw new IllegalArgumentException("Username cannot be empty");
    }
}

// ❌ خطأ
public void validateInput(String username,String password){
if(username.isEmpty()){throw new IllegalArgumentException("Username cannot be empty");}}
```

### التعليقات والتوثيق
```java
/**
 * وصف الدالة بالعربية
 * 
 * @param username اسم المستخدم
 * @param password كلمة المرور
 * @return جلسة المستخدم أو null
 */
public UserSession authenticate(String username, String password) {
    // التطبيق
}
```

### معالجة الأخطاء
```java
try {
    // العملية
} catch (SpecificException e) {
    logger.error("Specific error message", e);
    throw new RuntimeException("User-friendly error message", e);
} finally {
    // تنظيف الموارد
}
```

---

## 🔍 عملية المراجعة

### ما الذي سيتم فحصه:
1. ✅ جودة الكود
2. ✅ الاختبارات والتغطية
3. ✅ الأمان
4. ✅ الأداء
5. ✅ التوثيق
6. ✅ المعايير المرعية

### ملاحظات المراجعة
- كن منفتحاً للتعليقات البناءة
- اطلب توضيح إذا لم تفهم شيئاً
- رد على جميع التعليقات
- طبق التغييرات المطلوبة

---

## 📚 الموارد المفيدة

### التوثيق
- [README.md](README.md) - نظرة عامة
- [SECURITY.md](SECURITY.md) - سياسات الأمان
- [API Documentation](docs/API.md) - توثيق API

### الأدوات
- [Android Studio](https://developer.android.com/studio)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)

### الدورات والمراجع
- [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- [OWASP Security Guidelines](https://owasp.org/)
- [Android Development Best Practices](https://developer.android.com/docs)

---

## 🚫 الأشياء التي لا تفعلها

- ❌ لا تستخدم hard-coded values
- ❌ لا تترك debug prints
- ❌ لا تضيف ملفات كبيرة غير ضرورية
- ❌ لا تتجاهل الأخطاء
- ❌ لا تكتب كوداً معقداً بدون شرح
- ❌ لا تتجاهل معايير الأمان

---

## 💬 الحصول على المساعدة

### قنوات التواصل
- **GitHub Issues:** للأسئلة والمشاكل
- **Discussions:** للنقاش والأفكار
- **البريد:** shhdalbshary@gmail.com

### الأسئلة الشائعة
- كيف أعدل التوثيق؟
- كيف أشغل الاختبارات؟
- ماذا لو واجهت خطأ؟

---

## 🎯 دليل سريع

```bash
# إعداد المشروع
git clone https://github.com/shhdalbshary-wq/YMERP.git
cd YMERP
mvn clean install

# إنشاء فرع
git checkout -b feature/my-feature

# التعديل والاختبار
mvn clean install
mvn test

# الـ Commit والـ Push
git add .
git commit -m "feat: إضافة ميزة جديدة"
git push origin feature/my-feature

# فتح Pull Request على GitHub
```

---

## 🎉 شكراً!

شكراً لمساهمتك في YMERP! 🙏

أنت رائع! ⭐

---

**آخر تحديث:** 4 يونيو 2026
