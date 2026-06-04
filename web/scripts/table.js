// بيانات تجريبية (يمكن استبدالها ببيانات من API أو قاعدة بيانات)
const invoices = [
  { invoiceNumber: "INV001", customerName: "أحمد", amount: 500, date: "2026-06-01" },
  { invoiceNumber: "INV002", customerName: "سارة", amount: 1200, date: "2026-06-02" },
  { invoiceNumber: "INV003", customerName: "خالد", amount: 750, date: "2026-06-03" }
];

const tableBody = document.querySelector("#invoiceTable tbody");

// عرض البيانات في الجدول
function renderTable(data) {
  tableBody.innerHTML = "";
  data.forEach(item => {
    const row = `<tr>
      <td>${item.invoiceNumber}</td>
      <td>${item.customerName}</td>
      <td>${item.amount}</td>
      <td>${item.date}</td>
      <td>
        <button class="action-btn edit-btn" onclick="editInvoice('${item.invoiceNumber}')">✏️ تعديل</button>
        <button class="action-btn delete-btn" onclick="deleteInvoice('${item.invoiceNumber}')">🗑️ حذف</button>
        <button class="action-btn details-btn" onclick="viewDetails('${item.invoiceNumber}')">📄 تفاصيل</button>
      </td>
    </tr>`;
    tableBody.innerHTML += row;
  });
}

// البحث والتصفية
document.getElementById("searchInput").addEventListener("keyup", function() {
  const keyword = this.value.toLowerCase();
  const filtered = invoices.filter(inv =>
    inv.invoiceNumber.toLowerCase().includes(keyword) ||
    inv.customerName.toLowerCase().includes(keyword)
  );
  renderTable(filtered);
});

// وظائف التحكم
function editInvoice(id) {
  alert("تعديل الفاتورة: " + id);
}

function deleteInvoice(id) {
  alert("حذف الفاتورة: " + id);
}

function viewDetails(id) {
  alert("عرض تفاصيل الفاتورة: " + id);
}

// عرض أولي
renderTable(invoices);
