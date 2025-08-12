package com.example.demo.company.service;

import com.example.demo.company.service.request.RegisterCompanyRequest;
import com.example.demo.company.service.response.RegisterCompanyResponse;

public interface CompanyService {
    RegisterCompanyResponse register(RegisterCompanyRequest registerCompanyRequest);
}
