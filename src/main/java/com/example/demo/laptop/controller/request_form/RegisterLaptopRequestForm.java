package com.example.demo.laptop.controller.request_form;

import com.example.demo.laptop.service.request.RegisterLaptopRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterLaptopRequestForm {
    final private String company;
    final private String name;
    final private String content;
    final private Long price;

    public RegisterLaptopRequest toRegisterLaptopRequest(Long accountId) {
        return new RegisterLaptopRequest(company, name, content, price, accountId);
    }
}
