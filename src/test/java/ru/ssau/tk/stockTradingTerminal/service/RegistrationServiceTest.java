package ru.ssau.tk.stockTradingTerminal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssau.tk.stockTradingTerminal.model.Person;
import ru.ssau.tk.stockTradingTerminal.repository.PeopleRepository;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationServiceTest {
    @Autowired
    private RegistrationService registrationService;
    @MockBean
    private PeopleRepository peopleRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void register(){
        Person person = new Person();
        registrationService.register(person);
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(person.getPassword());
        Mockito.verify(peopleRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }
}