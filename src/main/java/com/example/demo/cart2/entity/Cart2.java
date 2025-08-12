package com.example.demo.cart2.entity;

import com.example.demo.account.entity.Account;
import com.example.demo.book2.entity.Book2;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Cart2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id" , nullable = false)
    private Book2 book;
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Cart2(Book2 book, Long quantity, Account account) {
        this.book = book;
        this.quantity = quantity;
        this.account = account;
    }

}
