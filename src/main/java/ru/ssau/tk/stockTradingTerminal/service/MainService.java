package ru.ssau.tk.stockTradingTerminal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MainService {
    @Autowired
    private final TransactionService transactionService;

    @Autowired
    private final PersonDetailsService personDetailsService;
    @Transactional
    public void createTransaction(){
        
    }

}
