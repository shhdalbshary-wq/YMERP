cat > InvoiceControllerTest.java <<'EOF'
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ACCOUNTANT") // يوفّر مستخدماً وهمياً لصلاحيات الوصول للاختبارات
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
EOF
