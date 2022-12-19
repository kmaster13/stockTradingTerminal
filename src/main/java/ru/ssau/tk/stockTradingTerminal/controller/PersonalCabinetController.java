package ru.ssau.tk.stockTradingTerminal.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ssau.tk.stockTradingTerminal.security.PersonDetails;

@Controller
public class PersonalCabinetController {

    @GetMapping("/personalCabinet")
    public String getHelloPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        model.addAttribute("person", personDetails.getPerson());
        return "personal_cabinet";
    }
}