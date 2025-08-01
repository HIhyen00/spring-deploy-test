package com.example.demo.account.controller.response_form;

import com.example.demo.account.service.response.RegisterAccountResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterAccountResponseForm {

    final private String nickname;
    final private String email;
    final private String userToken;

    public static RegisterAccountResponseForm from(RegisterAccountResponse response, String userToken) {
        return new RegisterAccountResponseForm(response.getNickname(), response.getEmail(), userToken);
    }

}
