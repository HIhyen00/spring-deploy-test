package com.example.demo.book2.controller.response_form;

import com.example.demo.book2.service.response.RegisterBook2Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterBook2ResponseForm {
    final private Long bookId;
    final private String title;
    final private String content;
    final private String registedAccountNickname;
    final private Long price;

    public static RegisterBook2ResponseForm from(RegisterBook2Response response) {
        return new RegisterBook2ResponseForm(response.getBookId(), response.getTitle(), response.getContent(), response.getRegisteredAccountNickname(), response.getPrice());
    }
}
