package com.example.demo.book2.controller.request_form;

import com.example.demo.book2.service.request.RegisterBook2Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RegisterBook2RequestForm {
    final private String title;
    final private String content;
    final private Long price;

    public RegisterBook2Request toRegisterBook2Request(){
        return new RegisterBook2Request(title,content,price);
    }

}
