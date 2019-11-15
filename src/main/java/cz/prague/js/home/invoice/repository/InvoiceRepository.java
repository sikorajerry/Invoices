package cz.prague.js.home.invoice.repository;

import cz.prague.js.home.invoice.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {

    List<Invoice> findByUsername(String loggedUsername);
}
