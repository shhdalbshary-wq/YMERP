@Service
public class ReportService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public ReportSummary generateSummary() {
        double totalSales = invoiceRepository.sumAllSales();
        double totalExpenses = expenseRepository.sumAllExpenses();
        return new ReportSummary(totalSales, totalExpenses, totalSales - totalExpenses);
    }

    public List<SalesReport> generateSalesReport(String startDate, String endDate) {
        return invoiceRepository.findSalesBetween(startDate, endDate);
    }

    public List<ExpenseReport> generateExpenseReport(String startDate, String endDate) {
        return expenseRepository.findExpensesBetween(startDate, endDate);
    }
}
