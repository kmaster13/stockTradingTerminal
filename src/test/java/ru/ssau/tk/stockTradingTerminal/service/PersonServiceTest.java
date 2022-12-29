package ru.ssau.tk.stockTradingTerminal.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssau.tk.stockTradingTerminal.model.Person;
import ru.ssau.tk.stockTradingTerminal.repository.PeopleRepository;
import ru.ssau.tk.stockTradingTerminal.security.PersonDetails;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @MockBean
    private PeopleRepository peopleRepository;

    @Test
    public void getAll(){
        List<Person> people1 = peopleRepository.findAll();
        List<Person> people2 = personService.getAll();
        Assert.assertEquals(people1,people2);
    }

    @Test
    public void getPerson(){
        int id1 = 1;
        Assert.assertEquals(personService.getPerson(id1),peopleRepository.findById(id1).orElse(null));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername(){
        String username = "user";
        Assert.assertEquals(personService.loadUserByUsername(username),new PersonDetails(peopleRepository.findByUsername(username).get()));
    }

    @Test
    public void savePerson(){
        Person person = new Person();
        personService.savePerson(person);
        Mockito.verify(peopleRepository,Mockito.times(1)).save(person);
    }

    @Test
    public void deletePerson(){
        int id = 2;
        personService.deletePerson(id);
        Mockito.verify(peopleRepository,Mockito.times(1)).deleteById(ArgumentMatchers.anyInt());
    }
}