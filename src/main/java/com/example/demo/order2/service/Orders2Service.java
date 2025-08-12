package com.example.demo.order2.service;

import com.example.demo.order2.service.request.CreateAllOrderItemRequest;
import com.example.demo.order2.service.request.CreateAllOrders2Request;
import com.example.demo.order2.service.request.ListOrdersRequest;
import com.example.demo.order2.service.response.CreateAllOrders2Response;
import com.example.demo.order2.service.response.ListOrdersResponse;

public interface Orders2Service {
    CreateAllOrders2Response createAll(
            CreateAllOrders2Request ordersRequest,
            CreateAllOrderItemRequest orderItemsRequest);
    ListOrdersResponse list(ListOrdersRequest ordersRequest);
}
