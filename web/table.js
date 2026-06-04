<!DOCTYPE html>
<html lang="ar">
<head>
  <meta charset="UTF-8">
  <title>قائمة الفواتير</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <div class="table-container">
    <h2>📊 قائمة الفواتير</h2>
    <input type="text" id="searchInput" placeholder="🔍 ابحث عن فاتورة أو عميل...">

    <table id="invoiceTable">
      <thead>
        <tr>
          <th>رقم الفاتورة</th>
          <th>اسم العميل</th>
          <th>المبلغ</th>
          <th>التاريخ</th>
          <th>إجراءات</th>
        </tr>
      </thead>
      <tbody>
        <!-- سيتم ملء البيانات ديناميكيًا عبر table.js -->
      </tbody>
    </table>
  </div>

  <script src="scripts/table.js"></script>
</body>
</html>
