cat > web/scripts/form.js <<'EOF'
// ربط النموذج بالحدث Submit
document.getElementById("invoiceForm").addEventListener("submit", function(e) {
  e.preventDefault();

  // جمع البيانات من الحقول
  const data = {
    customerName: document.getElementById("customerName").value,
    invoiceNumber: document.getElementById("invoiceNumber").value,
    amount: parseFloat(document.getElementById("amount").value),
    date: document.getElementById("date").value
  };

  // تحضير الرؤوس (بما في ذلك Authorization إذا وُجد توكن)
  const headers = { "Content-Type": "application/json" };
  const token = localStorage.getItem("token"); // تأكد من تخزين التوكن بعد تسجيل الدخول
  if (token) headers["Authorization"] = "Bearer " + token;

  const submitBtn = this.querySelector('button[type="submit"]');
  if (submitBtn) submitBtn.disabled = true;

  fetch("/api/invoices", {
    method: "POST",
    headers,
    body: JSON.stringify(data)
  })
  .then(async res => {
    if (submitBtn) submitBtn.disabled = false;
    const text = await res.text();
    let json = null;
    try { json = JSON.parse(text); } catch (e) { /* not JSON */ }
    if (!res.ok) {
      const errMsg = (json && json.message) ? json.message : `خطأ: ${res.status} ${res.statusText}`;
      throw new Error(errMsg);
    }
    return json;
  })
  .then(response => {
    document.getElementById("message").innerText = "✅ تم حفظ الفاتورة بنجاح";
    document.getElementById("invoiceForm").reset(); // تفريغ النموذج بعد الحفظ
  })
  .catch(err => {
    document.getElementById("message").innerText = "❌ حدث خطأ أثناء الحفظ: " + err.message;
    console.error(err);
  });
});
EOF
