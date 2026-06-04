// ربط النموذج بالحدث Submit
document.getElementById("invoiceForm").addEventListener("submit", function(e) {
  e.preventDefault();

  // جمع البيانات من الحقول
  const data = {
    customerName: document.getElementById("customerName").value,
    invoiceNumber: document.getElementById("invoiceNumber").value,
    amount: document.getElementById("amount").value,
    date: document.getElementById("date").value
  };

  // إرسال البيانات إلى الـ API
  fetch("/api/invoices", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  })
  .then(res => {
    if (!res.ok) throw new Error("خطأ في الاتصال بالسيرفر");
    return res.json();
  })
  .then(response => {
    document.getElementById("message").innerText = "✅ تم حفظ الفاتورة بنجاح";
    document.getElementById("invoiceForm").reset(); // تفريغ النموذج بعد الحفظ
  })
  .catch(err => {
    document.getElementById("message").innerText = "❌ حدث خطأ أثناء الحفظ";
    console.error(err);
  });
});
