package com.example.demo.book2.service;

import com.example.demo.book2.service.request.ListBookRequest;
import com.example.demo.book2.service.request.RegisterBook2Request;
import com.example.demo.book2.service.response.ListBookResponse;
import com.example.demo.book2.service.response.RegisterBook2Response;

public interface Book2Service {
    RegisterBook2Response register(RegisterBook2Request request, Long accountId);
    ListBookResponse list(ListBookRequest request);
}
