package cz.prague.js.home.invoice.controller;

import cz.prague.js.home.invoice.common.UserContextUtils;
import cz.prague.js.home.invoice.dto.InvoiceDto;
import cz.prague.js.home.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/invoices/")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("list")
    public String list(Model model) {
        model.addAttribute("invoices", invoiceService.findAll());
        return "invoices/list";
    }

    @GetMapping("userlist")
    public String userlist(Model model) {
        String loggedUsername = UserContextUtils.getLoggedUsername();

        model.addAttribute("invoices", invoiceService.findByUsername(loggedUsername));
        return "invoices/list";
    }

    @GetMapping("add")
    public String addUser(Model model) {
        model.addAttribute(new InvoiceDto());
        return "invoices/add";
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

}
