package com.example.demo.order2.service.request;

import com.example.demo.book2.entity.Book2;
import com.example.demo.order2.entity.OrderItem;
import com.example.demo.order2.entity.Orders2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrderItemRequest {
    final private Long bookId;
    final private Long quantity;
    final private Long price;

    public OrderItem toOrderItem(Book2 book, Orders2 orders) {
        return new OrderItem(book, quantity, price, orders);
    }
}
