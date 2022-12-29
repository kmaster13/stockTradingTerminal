package ru.ssau.tk.stockTradingTerminal.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssau.tk.stockTradingTerminal.model.Person;
import ru.ssau.tk.stockTradingTerminal.repository.PeopleRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @MockBean
    private PeopleRepository peopleRepository;

    @Test
    public void  getAllUsers(){
        List<Person> people1 = peopleRepository.findAll();
        List<Person> people2 = adminService.getAllUsers();
        Assert.assertEquals(people1,people2);
    }
}