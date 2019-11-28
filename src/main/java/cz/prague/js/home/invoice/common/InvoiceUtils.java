package cz.prague.js.home.invoice.common;

import cz.prague.js.home.invoice.dto.InvoiceDto;
import cz.prague.js.home.invoice.model.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceUtils {

    public static List<InvoiceDto> convertInvoiceListToInvoiceDtoList(List<Invoice> allInvoices) {
        List<InvoiceDto> output = new ArrayList<>();

        for (Invoice invoice: allInvoices) {
            InvoiceDto invoiceDto = new InvoiceDto();
            invoiceDto.setId(invoice.getId());
            invoiceDto.setProductName(invoice.getProductName());
            invoiceDto.setPrice(invoice.getPrice());
            invoiceDto.setBuyDate(invoice.getBuyDate());
            invoiceDto.setCompany(invoice.getCompany());
            invoiceDto.setCreated(invoice.getCreated());
            invoiceDto.setFileName(invoice.getFileName());
            invoiceDto.setFileId(invoice.getFileId());

            output.add(invoiceDto);
        }
        return output;
    }

    /**
     * pouziva se je pro save , proto jsem si dovolil ulozit do fileName file.getOriginalFilename
     * @param invoiceDto
     * @return
     */
    public static Invoice convertInvoiceDtoTOInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setBuyDate(invoiceDto.getBuyDate());
        invoice.setCompany(invoiceDto.getCompany());
        invoice.setCreated(LocalDate.now());
        invoice.setFileId(invoiceDto.getFileId());
        invoice.setFileName(invoiceDto.getFile().getOriginalFilename());
        invoice.setPrice(invoiceDto.getPrice());
        invoice.setProductName(invoiceDto.getProductName());

        return invoice;
    }
}
