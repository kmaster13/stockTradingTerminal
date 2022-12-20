package ru.ssau.tk.stockTradingTerminal.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Stock {
    String ticker;
    String figi;
    String name;
    String type;
    double price;
}