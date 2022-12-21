package ru.ssau.tk.stockTradingTerminal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.stockTradingTerminal.model.Person;
import ru.ssau.tk.stockTradingTerminal.model.Stock;
import ru.ssau.tk.stockTradingTerminal.model.Transaction;
import ru.ssau.tk.stockTradingTerminal.security.PersonDetails;
import ru.ssau.tk.stockTradingTerminal.service.StockService;
import ru.ssau.tk.stockTradingTerminal.service.TransactionService;

@Controller
@RequiredArgsConstructor
public class TradingTerminalController {
    @Autowired
    private final TransactionService transactionService;
    @Autowired
    private final StockService stockService;

    private Person getAuthPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }

    @GetMapping("/personalCabinet")
    public String getPersonalCabinetPage(Model model) {
        Person person = getAuthPerson();
        model.addAttribute("transactions", transactionService.getAllByPerson(person));
        model.addAttribute("person", person);
        return "personal_cabinet";
    }

    @GetMapping("/exchange")
    public String getStockExchangePage(Model model) {
        Person person = getAuthPerson();
        model.addAttribute("person", person);
        model.addAttribute("stocks", stockService.getDefaultStocks());
        return "exchange";
    }

    @GetMapping("/stocks/{ticker}")
    public String showStockByTicker(@PathVariable("ticker") String ticker, Model model) {
        model.addAttribute("stock", stockService.getStockByTicker(ticker));
        return "stocks/ticker";
    }

    @PostMapping("/stocks/{ticker}")
    public String buyStock(@PathVariable("ticker") String ticker, @RequestParam("amount") int amount){
        transactionService.saveTransaction(stockService.getStockByTicker(ticker), getAuthPerson(), amount);
        return "redirect:/exchange";
    }
}