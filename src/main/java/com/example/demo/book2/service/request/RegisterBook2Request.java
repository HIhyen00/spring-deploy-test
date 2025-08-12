package com.example.demo.book2.service.request;

import com.example.demo.account.entity.Account;
import com.example.demo.book2.entity.Book2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterBook2Request {
    final private String title;
    final private String content;
    final private Long price;

    public Book2 toBook2(Account account) {
        return new Book2(title, content, account, price);
    }
}
