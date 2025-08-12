package com.example.demo.company.service;

import com.example.demo.account.entity.Account;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.company.entity.Company;
import com.example.demo.company.repository.CompanyRepository;
import com.example.demo.company.service.request.RegisterCompanyRequest;
import com.example.demo.company.service.response.RegisterCompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    final private CompanyRepository companyRepository;
    final private AccountRepository accountRepository;

    @Override
    public RegisterCompanyResponse register(RegisterCompanyRequest request) {

        Long accountId = request.getAccountId();
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalAccount.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 사용자 입니다.");
        }

        Account account = optionalAccount.get();

        Company company = request.toCompany(account);
        Company savedCompany = companyRepository.save(company);
        return RegisterCompanyResponse.from(savedCompany);
    }
}
