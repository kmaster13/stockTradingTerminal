package ru.ssau.tk.stockTradingTerminal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ssau.tk.stockTradingTerminal.service.AdminService;

@Controller
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private final AdminService adminService;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("people", adminService.getAllUsers());
        return "admin";
    }
}
