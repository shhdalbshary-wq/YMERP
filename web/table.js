document.getElementById("invoiceForm").addEventListener("submit", function(e) {
  e.preventDefault();

  // جمع البيانات من الحقول
  const data = {
    customerName: document.getElementById("customerName").value,
    invoiceNumber: document.getElementById("invoiceNumber").value,
    amount: parseFloat(document.getElementById("amount").value),
    date: document.getElementById("date").value
  };

  // تحضير الرؤوس
  const headers = { "Content-Type": "application/json" };
  const token = localStorage.getItem("token"); // استرجاع التوكن
  if (token) headers["Authorization"] = "Bearer " + token;

  const submitBtn = this.querySelector('button[type="submit"]');
  submitBtn.disabled = true;

  fetch("/api/invoices", {
    method: "POST",
    headers,
    body: JSON.stringify(data)
  })
  .then(async res => {
    submitBtn.disabled = false;
    const text = await res.text();
    let json;
    try { json = JSON.parse(text); } catch(_) { json = null; }
    if (!res.ok) {
      const errMsg = (json && json.message) ? json.message : `خطأ: ${res.status} ${res.statusText}`;
      throw new Error(errMsg);
    }
    return json;
  })
  .then(response => {
    document.getElementById("message").innerText = "✅ تم حفظ الفاتورة بنجاح";
    document.getElementById("invoiceForm").reset();
  })
  .catch(err => {
    document.getElementById("message").innerText = "❌ حدث خطأ: " + err.message;
    console.error(err);
  });
});
