package com.example.demo.cart2.service.response;

import com.example.demo.book2.entity.Book2;
import com.example.demo.cart2.entity.Cart2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class CreateCart2Response {
    final private Long cartId;
    final private Long bookId;
    final private String bookTitle;
    final private Long quantity;

    public static CreateCart2Response from(final Cart2 cart) {
        Book2 responseBook = cart.getBook();

        return new CreateCart2Response(cart.getId(), responseBook.getId(), responseBook.getTitle(), cart.getQuantity());
    }
}
