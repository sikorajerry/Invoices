package cz.prague.js.home.invoice.service;

import cz.prague.js.home.invoice.common.InvoiceUtils;
import cz.prague.js.home.invoice.common.UserContextUtils;
import cz.prague.js.home.invoice.dto.InvoiceDto;
import cz.prague.js.home.invoice.model.Invoice;
import cz.prague.js.home.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private StorageService storageService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, StorageService storageService) {
        this.invoiceRepository = invoiceRepository;
        this.storageService = storageService;
    }

    public void delete(String id) {
        invoiceRepository.deleteById(id);
    }

    public List<InvoiceDto> findAll() {
        List<Invoice> allInvoices = invoiceRepository.findAll();
        return InvoiceUtils.convertInvoiceListToInvoiceDtoList(allInvoices);
    }

    public List<InvoiceDto> findByUsername(String loggedUsername) {
        List<Invoice> allInvoices = invoiceRepository.findByUsername(loggedUsername);
        return InvoiceUtils.convertInvoiceListToInvoiceDtoList(allInvoices);
    }

    public void save(InvoiceDto invoiceDto) {

        String fileId="";
        try {
            fileId = storageService.store(invoiceDto.getFile());
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        invoiceDto.setFileId(fileId);


        Invoice invoice = InvoiceUtils.convertInvoiceDtoTOInvoice(invoiceDto);
        invoice.setUsername(UserContextUtils.getLoggedUsername());

        invoiceRepository.save(invoice);
    }




}
