package com.example.demo.account.service.response;

import com.example.demo.account.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterAccountResponse {
    final private Long accountId;
    final private String nickname;
    final private String email;

    public static RegisterAccountResponse from(Account account) {
        return new RegisterAccountResponse(account.getId(), account.getNickname(), account.getEmail());
    }
}
