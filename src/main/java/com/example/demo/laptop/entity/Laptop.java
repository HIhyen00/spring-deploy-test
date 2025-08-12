package com.example.demo.laptop.entity;

import com.example.demo.account.entity.Account;
import com.example.demo.company.entity.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private String name;
    private String content;
    private Long price;

    public Laptop(Company company, Account account, String name, String content, Long price) {
        this.company = company;
        this.account = account;
        this.name = name;
        this.content = content;
        this.price = price;
    }
}
