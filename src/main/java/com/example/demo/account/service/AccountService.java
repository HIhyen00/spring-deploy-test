package com.example.demo.account.service;

import com.example.demo.account.entity.Account;
import com.example.demo.account.service.request.RegisterAccountRequest;
import com.example.demo.account.service.response.RegisterAccountResponse;

import java.util.Optional;

public interface AccountService {
    RegisterAccountResponse register(RegisterAccountRequest request);

    Optional<Account> find(String email);
}