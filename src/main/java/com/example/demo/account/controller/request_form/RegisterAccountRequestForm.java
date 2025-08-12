package com.example.demo.account.controller.request_form;

import com.example.demo.account.service.request.RegisterAccountRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterAccountRequestForm {
    // 넘어오는 정보
    final private String nickname;
    final private String email;

    public RegisterAccountRequest toRegisterAccountRequest() {
        return new RegisterAccountRequest(nickname, email);
    }
}
