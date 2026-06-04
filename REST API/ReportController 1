@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // تقرير ملخص عام
    @GetMapping("/summary")
    public ReportSummary getSummary() {
        return reportService.generateSummary();
    }

    // تقرير مبيعات حسب التاريخ
    @GetMapping("/sales")
    public List<SalesReport> getSalesReport(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return reportService.generateSalesReport(startDate, endDate);
    }

    // تقرير مصروفات
    @GetMapping("/expenses")
    public List<ExpenseReport> getExpenseReport(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return reportService.generateExpenseReport(startDate, endDate);
    }
}
