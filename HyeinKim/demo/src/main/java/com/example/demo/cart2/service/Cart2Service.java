package com.example.demo.cart2.service;

import com.example.demo.cart2.repository.Cart2Repository;
import com.example.demo.cart2.service.request.CreateCart2Request;
import com.example.demo.cart2.service.response.CreateCart2Response;

public interface Cart2Service {

    CreateCart2Response create(CreateCart2Request request, Long accountId);

}
