package ru.ssau.tk.stockTradingTerminal.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "balance")
    private double balance;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    public Person(String username, double balance, String password, String role) {
        this.username = username;
        this.balance = balance;
        this.password = password;
        this.role = role;
    }
}