package com.example.demo.company.controller.request_form;

import com.example.demo.company.service.request.RegisterCompanyRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterCompanyRequestForm {
    final private String name;
    final private String address;
    final private String number;

    public RegisterCompanyRequest toRegisterCompanyRequest(Long accountId) {
        return new RegisterCompanyRequest(name, address, number, accountId);
    }
}
