package com.example.demo.order2.controller;

import com.example.demo.order2.controller.request_form.CreateAllOrders2RequestForm;
import com.example.demo.order2.controller.response_form.ListOrdersResponseForm;
import com.example.demo.order2.controller.response_form.CreateAllOrders2ResponseForm;
import com.example.demo.order2.controller.request_form.ListOrdesrRequestForm;
import com.example.demo.order2.service.Orders2Service;
import com.example.demo.order2.service.request.CreateAllOrderItemRequest;
import com.example.demo.order2.service.request.CreateAllOrders2Request;
import com.example.demo.order2.service.request.ListOrdersRequest;
import com.example.demo.order2.service.response.CreateAllOrders2Response;
import com.example.demo.order2.service.response.ListOrdersResponse;
import com.example.demo.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order2")
public class Orders2Controller {

    final private Orders2Service ordersService;
    private final RedisCacheService redisCacheService;

//    @PostMapping("/create")
//    public CreateOrders2ResponseForm createOrder(
//            @RequestHeader("Authorization") String authorizedHeader,
//            @RequestBody CreateOrders2RequestForm requestForm) {
//        log.info("createOrders()");
//
//        String userToken = authorizedHeader.replace("Bearer ", "").trim();
//        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);
//
//        CreateOrders2Request request = requestForm.toCreateOrdersRequest(accountId);
//        CreateOrders2Response response = ordersService.create(request);
//
//        return CreateOrders2ResponseForm.from(response);
//    }

    @PostMapping("/create-all")
    public CreateAllOrders2ResponseForm createAllOrder(
            @RequestHeader("Authorization") String authorizedHeader,
            @RequestBody CreateAllOrders2RequestForm requestForm) {
        log.info("createAllOrders()");

        String userToken = authorizedHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        CreateAllOrders2Request ordersRequest = requestForm.toCreateAllOrders2Request(accountId);
        CreateAllOrderItemRequest orderItemRequest = requestForm.toCreateAllOrderItemRequest();
        CreateAllOrders2Response response = ordersService.createAll(ordersRequest, orderItemRequest);

        return CreateAllOrders2ResponseForm.from(response);
    }

    @GetMapping("/list")
    public ListOrdersResponseForm orderList (@RequestHeader("Authorization")String authorizedHeader, @ModelAttribute ListOrdesrRequestForm requestForm) {
        log.info("listOrders()");

        String userToken = authorizedHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        ListOrdersRequest request = requestForm.toListOrdersRequest(accountId);
        ListOrdersResponse response = ordersService.list(request);

        return  ListOrdersResponseForm.from(response);

    }

}
