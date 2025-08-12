package com.example.demo.order2.service.response;

import com.example.demo.order2.entity.OrderItem;
import com.example.demo.order2.entity.Orders2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CreateAllOrders2Response {
    final private Long ordersId;
    final private Integer itemCount;
    final private List<CreateOrderItemResponse> itemList;

    public static CreateAllOrders2Response from(final Orders2 orders, List<OrderItem> savedOrderItemList) {
        List<CreateOrderItemResponse> itemResponseList = savedOrderItemList.stream()
                .map(CreateOrderItemResponse::from)
                .collect(Collectors.toList());

        return new CreateAllOrders2Response( orders.getId(), itemResponseList.size(), itemResponseList);
    }

}
