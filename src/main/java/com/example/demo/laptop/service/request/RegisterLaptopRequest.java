package com.example.demo.laptop.service.request;

import com.example.demo.account.entity.Account;
import com.example.demo.company.entity.Company;
import com.example.demo.laptop.entity.Laptop;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterLaptopRequest {
    final private String company;
    final private String name;
    final private String content;
    final private Long price;
    final private Long accountId;

    public Laptop toLaptop(Company company, Account account) {
        return new Laptop(company, account, name, content, price);
    }

}
