package com.example.demo.company.controller;

import com.example.demo.company.controller.request_form.RegisterCompanyRequestForm;
import com.example.demo.company.controller.response_form.RegisterCompanyResponseForm;
import com.example.demo.company.service.CompanyService;
import com.example.demo.company.service.request.RegisterCompanyRequest;
import com.example.demo.company.service.response.RegisterCompanyResponse;
import com.example.demo.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    final private CompanyService companyService;
    final private RedisCacheService redisCacheService;

    @PostMapping("/register")
    public RegisterCompanyResponseForm createAllOrder(
            @RequestHeader("Authorization") String authorizedHeader,
            @RequestBody RegisterCompanyRequestForm requestForm) {
        log.info("createAllOrders()");

        String userToken = authorizedHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        RegisterCompanyRequest request = requestForm.toRegisterCompanyRequest(accountId);
        RegisterCompanyResponse response = companyService.register(request);

        return RegisterCompanyResponseForm.from(response);
    }
}
