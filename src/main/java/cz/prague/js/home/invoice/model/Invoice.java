package cz.prague.js.home.invoice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Invoice {
    private String id;
    private LocalDate created;
    private LocalDate buyDate;
    private String productName;
    private String company;
    private BigDecimal price;
    private String username;
}
