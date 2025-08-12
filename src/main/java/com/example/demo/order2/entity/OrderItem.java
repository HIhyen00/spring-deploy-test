package com.example.demo.order2.entity;

import com.example.demo.book2.entity.Book2;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders2 orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book2 book;

    private Long quantity;
    private Long price;

    public OrderItem(Book2 book, Long quantity, Long price, Orders2 orders) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
        this.orders = orders;
    }
}
