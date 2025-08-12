package com.example.demo.laptop.service;

import com.example.demo.account.entity.Account;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.company.entity.Company;
import com.example.demo.company.repository.CompanyRepository;
import com.example.demo.company.service.CompanyService;
import com.example.demo.laptop.entity.Laptop;
import com.example.demo.laptop.repository.LaptopRepository;
import com.example.demo.laptop.service.request.RegisterLaptopRequest;
import com.example.demo.laptop.service.response.RegisterLaptopResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaptopServiceImpl implements LaptopService {

    final private LaptopRepository laptopRepository;
    final private AccountRepository accountRepository;
    private final CompanyRepository companyRepository;

    @Override
    public RegisterLaptopResponse register(RegisterLaptopRequest request) {

        Long accountId = request.getAccountId();
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        String companyName = request.getCompany();
        Optional<Company> optionalCompany = companyRepository.findByName(companyName);

        if (optionalCompany.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 회사입니다.");
        }

        Company company = optionalCompany.get();

        // account에 @ManyToOne 으로 속한 회사의 정보를 담고,
        // account의 Company와 laptop의 제조사가 같은지 확인 후,
        // 같으면 저장, 같지 않으면 권한이 없음을 고지

        Laptop requestLaptop = request.toLaptop(company, account);
        Laptop savedLaptop = laptopRepository.save(requestLaptop);

        return RegisterLaptopResponse.from(savedLaptop);
    }
}
