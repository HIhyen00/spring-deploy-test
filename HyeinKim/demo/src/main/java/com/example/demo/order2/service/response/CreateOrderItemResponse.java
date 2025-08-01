package com.example.demo.order2.service.response;

import com.example.demo.book2.entity.Book2;
import com.example.demo.order2.entity.OrderItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrderItemResponse {
    final private Long bookId;
    final private String bookTitle;
    final private Long quantity;
    final private Long price;

    public static CreateOrderItemResponse from(final OrderItem orderItem) {
        Book2 orderedBook = orderItem.getBook();

        return new CreateOrderItemResponse(
                orderedBook.getId(),
                orderedBook.getTitle(),
                orderItem.getQuantity(),
                orderItem.getPrice());
    }
}
