package com.example.demo.cart2.service.request;

import com.example.demo.account.entity.Account;
import com.example.demo.book2.entity.Book2;
import com.example.demo.cart2.entity.Cart2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCart2Request {
    private final Long bookId;
    private final Long quantity;

    // Entity에서 @ManyToOne을 한..
    public Cart2 toCart(Book2 book, Account account) {
            return new Cart2(book, quantity, account);
    }
}
