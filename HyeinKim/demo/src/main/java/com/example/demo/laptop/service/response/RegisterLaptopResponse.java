package com.example.demo.laptop.service.response;

import com.example.demo.laptop.entity.Laptop;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterLaptopResponse {
    final private String name;
    final private String content;
    final private Long price;
    final private String companyName;

    public static RegisterLaptopResponse from(Laptop laptop) {
        return new RegisterLaptopResponse(
                laptop.getName(),
                laptop.getContent(),
                laptop.getPrice(),
                laptop.getCompany().getName());
    }
}
