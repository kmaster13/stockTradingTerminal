package ru.ssau.tk.stockTradingTerminal.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Stock {
    @Id
    @Column(name = "ticker")
    String ticker;
    @Column(name = "name")
    String name;
    @Column(name = "price")
    double price;
}