package com.example.demo.laptop.controller;

import com.example.demo.laptop.controller.request_form.RegisterLaptopRequestForm;
import com.example.demo.laptop.controller.response_form.RegisterLaptopResponseForm;
import com.example.demo.laptop.service.LaptopService;
import com.example.demo.laptop.service.request.RegisterLaptopRequest;
import com.example.demo.laptop.service.response.RegisterLaptopResponse;
import com.example.demo.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/laptop")
public class LaptopController {

    final private RedisCacheService redisCacheService;
    final private LaptopService laptopService;

    @PostMapping("/register")
    public RegisterLaptopResponseForm register (
            @RequestHeader("Authorization")String authorizedHeader,
            @RequestBody RegisterLaptopRequestForm requestForm) {

        String userToken = authorizedHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        RegisterLaptopRequest request = requestForm.toRegisterLaptopRequest(accountId);
        RegisterLaptopResponse response = laptopService.register(request);

        return RegisterLaptopResponseForm.from(response);
    }

}
