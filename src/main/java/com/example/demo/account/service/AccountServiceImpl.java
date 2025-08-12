package com.example.demo.account.service;

import com.example.demo.account.entity.Account;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.account.service.request.RegisterAccountRequest;
import com.example.demo.account.service.response.RegisterAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    final private AccountRepository accountRepository; // ***

    @Override
    public RegisterAccountResponse register(RegisterAccountRequest request) {
        // ***
        Account requestedAccount = request.toAccount();
        Account savedAccount = accountRepository.save(requestedAccount);

        return RegisterAccountResponse.from(savedAccount);
    }

    @Override
    public Optional<Account> find(String email) {
        return accountRepository.findByEmail(email);
    }
}
