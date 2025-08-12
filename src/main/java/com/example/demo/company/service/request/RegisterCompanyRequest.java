package com.example.demo.company.service.request;

import com.example.demo.account.entity.Account;
import com.example.demo.company.entity.Company;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterCompanyRequest {
    final private String name;
    final private String address;
    final private String number;
    final private Long accountId;

    public Company toCompany(Account account) {
        return new Company(name, address, number, account);
    }
 }
