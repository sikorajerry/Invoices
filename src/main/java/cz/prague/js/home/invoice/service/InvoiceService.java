package cz.prague.js.home.invoice.service;

import cz.prague.js.home.invoice.common.UserContextUtils;
import cz.prague.js.home.invoice.dto.InvoiceDto;
import cz.prague.js.home.invoice.model.Invoice;
import cz.prague.js.home.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<InvoiceDto> findAll() {
        List<Invoice> allInvoices = invoiceRepository.findAll();
        return getInvoiceDtos(allInvoices);
    }

    private List<InvoiceDto> getInvoiceDtos(List<Invoice> allInvoices) {
        List<InvoiceDto> output = new ArrayList<>();

        for (Invoice invoice: allInvoices) {
            InvoiceDto invoiceDto = new InvoiceDto();
            invoiceDto.setId(invoice.getId());
            invoiceDto.setProductName(invoice.getProductName());
            invoiceDto.setPrice(invoice.getPrice());
            invoiceDto.setBuyDate(invoice.getBuyDate());
            invoiceDto.setCompany(invoice.getCompany());
            invoiceDto.setCreated(invoice.getCreated());

            output.add(invoiceDto);
        }
        return output;
    }

    public void save(InvoiceDto invoiceDto) {

        String loggedUsername = UserContextUtils.getLoggedUsername();

        Invoice invoice = new Invoice();
        invoice.setBuyDate(invoiceDto.getBuyDate());
        invoice.setCompany(invoiceDto.getCompany());
        invoice.setCreated(LocalDate.now());
        invoice.setPrice(invoiceDto.getPrice());
        invoice.setProductName(invoiceDto.getProductName());
        invoice.setUsername(loggedUsername);

        invoiceRepository.save(invoice);
    }

    public void delete(String id) {
        invoiceRepository.deleteById(id);
    }

    public List<InvoiceDto> findByUsername(String loggedUsername) {
        List<Invoice> allInvoices = invoiceRepository.findByUsername(loggedUsername);
        return getInvoiceDtos(allInvoices);
    }
}
