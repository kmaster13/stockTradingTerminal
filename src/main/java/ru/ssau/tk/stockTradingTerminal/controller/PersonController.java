package ru.ssau.tk.stockTradingTerminal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ssau.tk.stockTradingTerminal.model.Person;
import ru.ssau.tk.stockTradingTerminal.security.PersonDetails;
import ru.ssau.tk.stockTradingTerminal.service.TransactionService;

@Controller
@RequiredArgsConstructor
public class PersonController {
    @Autowired
    private final TransactionService transactionService;
    @GetMapping("/personalCabinet")
    public String getPersonalCabinetPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = personDetails.getPerson();
        model.addAttribute("transactions", transactionService.getAllByPerson(person));
        model.addAttribute("person", person);
        return "personal_cabinet";
    }
    @GetMapping("/exchange")
    public String getStockExchangePage(){

        return "exchange";
    }
}