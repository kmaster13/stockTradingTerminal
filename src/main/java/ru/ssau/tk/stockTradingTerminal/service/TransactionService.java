package ru.ssau.tk.stockTradingTerminal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.tk.stockTradingTerminal.model.Person;
import ru.ssau.tk.stockTradingTerminal.model.Stock;
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
    @Autowired
    private final PersonService personService;


    @Transactional
    public Transaction getTransaction(int id) {
        Optional<Transaction> transaction = transactionsRepository.findById(id);
        return transaction.orElse(null);
    }


    @Transactional
    public void createTransaction(Stock stock, Person person, int amount) {
        person.setBalance(person.getBalance() - stock.getPrice() * amount);
        personService.savePerson(person);
        transactionsRepository.save(new Transaction(person, stock, amount, stock.getPrice()));
    }

    @Transactional
    public void sellTransaction(int id, int amount, Person person) {
        Transaction transaction = getTransaction(id);
        person.setBalance(person.getBalance() + transaction.getStock().getPrice() * amount);
        personService.savePerson(person);
        int transactionAmount = transaction.getAmount() - amount;
        if (transactionAmount == 0) {
            transactionsRepository.deleteById(id);
        } else if (transactionAmount > 0) {
            transactionsRepository.save(new Transaction(id, person, transaction.getStock(),
                    transactionAmount, transaction.getPrice()));
        }
    }


    @Transactional
    public List<Transaction> getAllByPerson(Person person) {
        return transactionsRepository.getAllByPerson(person);
    }

    @Transactional
    public List<Transaction> getAllByPersonAndStock(Person person, Stock stock) {
        return transactionsRepository.getAllByPersonAndStock(person, stock);
    }
}