package cz.prague.js.home.invoice.dto;

import cz.prague.js.home.invoice.model.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvoiceDto {
    private String id;
    private LocalDate created;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;
    private String productName;
    private String company;
    private BigDecimal price;
}
