package com.example.demo.book2.entity;


import com.example.demo.account.entity.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "book2")
public class Book2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private Long price;

    public Book2(String title, String content, Account account, Long price) {
        this.title = title;
        this.content = content;
        this.account = account;
        this.price = price;
    }
}
