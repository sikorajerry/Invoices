package cz.prague.js.home.invoice.controller;

import cz.prague.js.home.invoice.common.ControllerHelperEnum;
import cz.prague.js.home.invoice.common.UserContextUtils;
import cz.prague.js.home.invoice.dto.InvoiceDto;
import cz.prague.js.home.invoice.model.GoogleExtensionFile;
import cz.prague.js.home.invoice.model.User;
import cz.prague.js.home.invoice.service.InvoiceService;
import cz.prague.js.home.invoice.service.StorageService;
import cz.prague.js.home.invoice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Controller
@RequestMapping("/")
public class InvoiceController {
    private static final String INVOICE_PAGE_TEMPLATE = "invoices/list";

    Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    private InvoiceService invoiceService;
    private StorageService storageService;
    private UserService userService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, StorageService storageService, UserService userService) {
        this.invoiceService = invoiceService;
        this.storageService = storageService;
        this.userService = userService;
    }

    @GetMapping("allIvnoicesAllUsers")
    public String list(Model model) {
        model.addAttribute("invoices", invoiceService.findAll());
        return "invoices/list";
    }

    @GetMapping("userIvoices")
    public String userlist(Model model) throws Exception {

        model.addAttribute("invoices", invoiceService.findByUsername(UserContextUtils.getLoggedUsername()));
        model.addAttribute(ControllerHelperEnum.TEMPLATE.getName(), INVOICE_PAGE_TEMPLATE);
        model.addAttribute("userfullname", getUserFullName());
        return ControllerHelperEnum.BASE_PAGE.getName();
    }

    @GetMapping("insertInvoice")
    public String insertInvoice(Model model) throws Exception {
        model.addAttribute(new InvoiceDto());
        model.addAttribute("userfullname", getUserFullName());
        model.addAttribute(ControllerHelperEnum.TEMPLATE.getName(), "invoices/add");
        return  ControllerHelperEnum.BASE_PAGE.getName();
    }

    @PostMapping("add")
    public String addInvoice(@Valid @ModelAttribute InvoiceDto invoiceDto, BindingResult result) {
        if (result.hasErrors()) {
            return "invoices/add";
        }

        invoiceService.save(invoiceDto);
        return "redirect:userlist";
    }

    @PostMapping("delete")
    public String handleDeleteUser(String id) {
        invoiceService.delete(id);
        return "redirect:userlist";
    }

    @GetMapping("download/{id}")
    public ResponseEntity<byte[]> handleDownloadFileGet(@PathVariable String id) throws IOException, GeneralSecurityException {
        GoogleExtensionFile googleExtensionFile = storageService.getFileByName(id);
        byte[] imageBytes = googleExtensionFile.getByteArrayOutputStream().toByteArray();

        logger.info("Stahuji soubor {}", id);


        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.valueOf(googleExtensionFile.getExtensionData().getMimeType()));
        respHeaders.setContentLength(imageBytes.length);
        respHeaders.setContentDispositionFormData("attachment", googleExtensionFile.getExtensionData().getName());


        return  new ResponseEntity<>(imageBytes, respHeaders, HttpStatus.OK);

    }

    private String getUserFullName() throws Exception {
        String loggedUsername = UserContextUtils.getLoggedUsername();
        User userByUsername = userService.findUserByUsername(loggedUsername);

        return userByUsername.getName()+" "+userByUsername.getSurname();
    }


}
