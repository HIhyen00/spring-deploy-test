package com.example.demo.company.controller.response_form;

import com.example.demo.company.service.response.RegisterCompanyResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterCompanyResponseForm {
    final private String name;
    final private String email;

    public static RegisterCompanyResponseForm from(RegisterCompanyResponse response) {
        return new RegisterCompanyResponseForm(response.getName(), response.getEmail());
    }
}
