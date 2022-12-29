package ru.ssau.tk.stockTradingTerminal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ssau.tk.stockTradingTerminal.model.Person;
import ru.ssau.tk.stockTradingTerminal.model.Stock;
import ru.ssau.tk.stockTradingTerminal.model.Transaction;
import ru.ssau.tk.stockTradingTerminal.repository.PeopleRepository;
import ru.ssau.tk.stockTradingTerminal.security.PersonDetails;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {
    @Autowired
    private final PeopleRepository peopleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);

        if(person.isEmpty())
            throw new UsernameNotFoundException("User not found!");
        return new PersonDetails(person.get());
    }

    @Transactional
    public List<Person> getAll() {
        return peopleRepository.findAll();
    }

    @Transactional
    public Person getPerson(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    @Transactional
    public void savePerson(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }
}