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
    @Column(name = "ticker")
    private String ticker;
    @Column(name = "amount")
    private int amount;
    @Column(name = "purchase_price")
    private int purchasePrice;
    @Column(name = "current_price")
    private int currentPrice;
}