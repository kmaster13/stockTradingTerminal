package ru.ssau.tk.stockTradingTerminal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.tk.stockTradingTerminal.model.Person;
import ru.ssau.tk.stockTradingTerminal.repository.PeopleRepository;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    @Autowired
    private final PeopleRepository peopleRepository;

    public List<Person> getAllUsers() {
        return peopleRepository.findAll();
    }
}
