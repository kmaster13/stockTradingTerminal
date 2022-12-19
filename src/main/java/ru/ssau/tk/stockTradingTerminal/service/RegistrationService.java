package ru.ssau.tk.stockTradingTerminal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ssau.tk.stockTradingTerminal.model.Person;
import ru.ssau.tk.stockTradingTerminal.repository.PeopleRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    @Autowired
    private final PeopleRepository peopleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        person.setBalance(100000);
        peopleRepository.save(person);
    }
}