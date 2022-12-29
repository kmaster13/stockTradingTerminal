package ru.ssau.tk.stockTradingTerminal.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssau.tk.stockTradingTerminal.model.Person;
import ru.ssau.tk.stockTradingTerminal.model.Stock;
import ru.ssau.tk.stockTradingTerminal.model.Transaction;
import ru.ssau.tk.stockTradingTerminal.repository.TransactionsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;
    @MockBean
    private TransactionsRepository transactionsRepository;
    @MockBean
    private PersonService personService;

    @Test
    public void getTransaction() {
        int id = 5;
        Assert.assertEquals(transactionService.getTransaction(id), transactionsRepository.findById(id).orElse(null));
    }

    @Test
    public void createTransaction() {
        Person person = new Person();
        Stock stock = new Stock();
        int amount = 25;
        transactionService.createTransaction(stock, person, amount);
        Mockito.verify(personService, Mockito.times(1)).savePerson(ArgumentMatchers.any());
        Mockito.verify(transactionsRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test(expected = NullPointerException.class)
    public void sellTransaction() {
        int id = 3;
        int amount = 35;
        Person person = new Person();
        Transaction transaction = transactionService.getTransaction(id);
        transactionService.sellTransaction(id, amount, person);
        Mockito.verify(personService, Mockito.times(1)).savePerson(ArgumentMatchers.any());
        int transactionAmount = transaction.getAmount() - amount;
        if (transactionAmount == 0) {
            Mockito.verify(transactionsRepository, Mockito.times(1)).deleteById(id);
        } else if (transactionAmount > 0) {
            Mockito.verify(transactionsRepository, Mockito.times(1)).save(ArgumentMatchers.any());
        }
    }

    @Test
    public void getAllByPerson() {
        Person person = new Person();
        Assert.assertEquals(transactionService.getAllByPerson(person), transactionsRepository.getAllByPerson(person));
    }

    @Test
    public void getAllByPersonAndStock() {
        Person person = new Person();
        Stock stock = new Stock();
        Assert.assertEquals(transactionService.getAllByPersonAndStock(person, stock), transactionsRepository.getAllByPersonAndStock(person, stock));
    }
}