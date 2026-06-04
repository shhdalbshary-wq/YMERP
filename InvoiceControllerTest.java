@SpringBootTest
@AutoConfigureMockMvc
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetInvoices() throws Exception {
        mockMvc.perform(get("/api/invoices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testCreateInvoice() throws Exception {
        String invoiceJson = "{ \"customerName\": \"شركة الأمل\", \"amount\": 2500.75, \"date\": \"2026-06-04\" }";

        mockMvc.perform(post("/api/invoices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invoiceJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("شركة الأمل"));
    }
}
