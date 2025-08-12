package com.example.demo.cart2.controller;

import com.example.demo.cart2.controller.request_form.CreateCart2RequestForm;
import com.example.demo.cart2.controller.response_form.CreateCart2ResponseForm;
import com.example.demo.cart2.service.Cart2Service;
import com.example.demo.cart2.service.request.CreateCart2Request;
import com.example.demo.cart2.service.response.CreateCart2Response;
import com.example.demo.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart2")
public class Cart2Controller {

    private final Cart2Service cart2Service;
    private final RedisCacheService redisCacheService;

    @PostMapping("/create")
    public CreateCart2ResponseForm create (@RequestHeader("Authorization")String authorizedHeader, @RequestBody CreateCart2RequestForm requestForm) {
        log.info("requestform: {} " + requestForm);

        String userToken = authorizedHeader.replace("Bearer ", "");
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        CreateCart2Request request = requestForm.toCreateCart2Request();
        CreateCart2Response response = cart2Service.create(request, accountId);

        return CreateCart2ResponseForm.from(response);
    }
}
