package com.example.demo.book2.controller;

import com.example.demo.book2.controller.request_form.ListBookRequestForm;
import com.example.demo.book2.controller.request_form.RegisterBook2RequestForm;
import com.example.demo.book2.controller.response_form.ListBookResponseForm;
import com.example.demo.book2.controller.response_form.RegisterBook2ResponseForm;
import com.example.demo.book2.service.Book2Service;
import com.example.demo.book2.service.request.ListBookRequest;
import com.example.demo.book2.service.request.RegisterBook2Request;
import com.example.demo.book2.service.response.ListBookResponse;
import com.example.demo.book2.service.response.RegisterBook2Response;
import com.example.demo.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book2")
public class Book2Controller {

    private final Book2Service book2Service;
    private final RedisCacheService redisCacheService;

    @PostMapping("/register")
    public RegisterBook2ResponseForm register(@RequestHeader("Authorization")String authorizationHeader, @RequestBody RegisterBook2RequestForm requestForm) {
        log.info("registerBook() -> requestForm: {}", requestForm);

        String userToken = authorizationHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        RegisterBook2Request request = requestForm.toRegisterBook2Request();
        RegisterBook2Response response = book2Service.register(request, accountId);

        return RegisterBook2ResponseForm.from(response);
    }

    @GetMapping("/list")
    // list이지만 param을 받고 있음
    public ListBookResponseForm bookList(@ModelAttribute ListBookRequestForm requestForm) {
        log.info("bookList() -> requestForm: {}", requestForm);

        ListBookRequest request = requestForm.toListBookRequest();
        ListBookResponse response = book2Service.list(request);

        return ListBookResponseForm.from(response);
    }
}
