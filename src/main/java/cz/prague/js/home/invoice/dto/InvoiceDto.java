package cz.prague.js.home.invoice.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvoiceDto {
    private String id;
    private LocalDate created;
    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate buyDate;
    @NotEmpty
    private String productName;
    @NotEmpty
    private String company;
    @NotNull
    private BigDecimal price;
    private MultipartFile file;
    private String fileName;
    private String fileId;
}
