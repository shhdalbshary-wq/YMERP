@Service
public class ReportExportService {

    @Autowired
    private ReportService reportService;

    // تصدير ملخص التقرير إلى PDF
    public byte[] exportSummaryToPdf() throws IOException {
        ReportSummary summary = reportService.generateSummary();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        document.add(new Paragraph("تقرير الملخص العام"));
        document.add(new Paragraph("إجمالي المبيعات: " + summary.getTotalSales()));
        document.add(new Paragraph("إجمالي المصروفات: " + summary.getTotalExpenses()));
        document.add(new Paragraph("الربح الصافي: " + summary.getNetProfit()));
        document.close();

        return out.toByteArray();
    }

    // تصدير التقرير إلى Excel
    public byte[] exportSummaryToExcel() throws IOException {
        ReportSummary summary = reportService.generateSummary();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Summary");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("المبيعات");
        header.createCell(1).setCellValue("المصروفات");
        header.createCell(2).setCellValue("الربح الصافي");

        Row data = sheet.createRow(1);
        data.createCell(0).setCellValue(summary.getTotalSales());
        data.createCell(1).setCellValue(summary.getTotalExpenses());
        data.createCell(2).setCellValue(summary.getNetProfit());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return out.toByteArray();
    }
          }
