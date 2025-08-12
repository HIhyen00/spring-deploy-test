package com.example.demo.account.service.request;

import com.example.demo.account.entity.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class RegisterAccountRequest {
    private String nickname;
    private String email;

    public RegisterAccountRequest() {}
    public RegisterAccountRequest(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    // 추가!! ---- LoginType, AccountType 고려
    public Account toAccount(){
        return new Account(nickname, email);
    }
}
