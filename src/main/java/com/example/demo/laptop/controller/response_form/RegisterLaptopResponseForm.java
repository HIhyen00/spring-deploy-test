package com.example.demo.laptop.controller.response_form;

import com.example.demo.laptop.service.response.RegisterLaptopResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterLaptopResponseForm {
    final private String name;
    final private String content;
    final private Long price;
    final private String companyName;

    public static RegisterLaptopResponseForm from(RegisterLaptopResponse response) {
        return new RegisterLaptopResponseForm(
                response.getName(),
                response.getContent(),
                response.getPrice(),
                response.getCompanyName()
        );
    }
}
