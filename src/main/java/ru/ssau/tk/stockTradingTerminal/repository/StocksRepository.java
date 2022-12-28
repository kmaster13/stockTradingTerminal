package ru.ssau.tk.stockTradingTerminal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.tk.stockTradingTerminal.model.Stock;

@Repository
public interface StocksRepository extends JpaRepository<Stock, Integer> {
}
