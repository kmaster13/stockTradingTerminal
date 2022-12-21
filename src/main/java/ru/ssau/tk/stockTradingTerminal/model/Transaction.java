package ru.ssau.tk.stockTradingTerminal.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
    @ManyToOne
    @JoinColumn(name = "stock_ticker", referencedColumnName = "ticker")
    private Stock stock;
    @Column(name = "amount")
    private int amount;

    public Transaction(Person person, Stock stock, int amount) {
        this.person = person;
        this.stock = stock;
        this.amount = amount;
    }
}