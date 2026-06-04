@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/summary")
    public ReportSummary getSummary() {
        return reportService.generateSummary();
    }
}
