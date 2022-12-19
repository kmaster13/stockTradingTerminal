package ru.ssau.tk.stockTradingTerminal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.tk.stockTradingTerminal.model.Transaction;
import ru.ssau.tk.stockTradingTerminal.repository.TransactionsRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    @Autowired
    private final TransactionsRepository transactionsRepository;

    @Transactional
    public List<Transaction> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    @Transactional
    public Transaction getTransaction(int id) {
        Optional<Transaction> transaction = transactionsRepository.findById(id);
        return transaction.orElse(null);
    }

    @Transactional
    public void saveTransaction(Transaction transaction) {
        transactionsRepository.save(transaction);
    }

    @Transactional
    public void deleteTransaction(int id) {
        transactionsRepository.deleteById(id);
    }
}