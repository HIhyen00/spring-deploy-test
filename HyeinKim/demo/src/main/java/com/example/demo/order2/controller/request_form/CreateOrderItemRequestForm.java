package com.example.demo.order2.controller.request_form;

import com.example.demo.order2.service.request.CreateOrderItemRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrderItemRequestForm {
    final private Long bookId;
    final private Long quantity;
    final private Long price;

    public CreateOrderItemRequest toCreateOrderItemRequest() {
        return new CreateOrderItemRequest(bookId, quantity, price);
    }
}
