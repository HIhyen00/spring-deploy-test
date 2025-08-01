package com.example.demo.book2.service.response;

import com.example.demo.book2.entity.Book2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterBook2Response {
    final private Long bookId;
    final private String title;
    final private String content;
    final private String registeredAccountNickname;
    final private Long price;


    public static RegisterBook2Response from(final Book2 book) {
        return new RegisterBook2Response(book.getId(), book.getTitle(), book.getContent(), book.getAccount().getNickname(), book.getPrice());
    }
}
