package com.example.demo.company.service.response;

import com.example.demo.company.entity.Company;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterCompanyResponse {
    final private String name;
    final private String email;

    public static RegisterCompanyResponse from(Company company) {
        return new RegisterCompanyResponse(company.getName(), company.getAccount().getEmail());
    }
}
