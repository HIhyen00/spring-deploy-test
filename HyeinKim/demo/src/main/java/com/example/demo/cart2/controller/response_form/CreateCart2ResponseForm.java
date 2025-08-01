package com.example.demo.cart2.controller.response_form;

import com.example.demo.cart2.service.response.CreateCart2Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCart2ResponseForm {
    final private Long cartId;
    final private Long bookId;
    final private String bookTitle;
    final private Long quantity;

    public static CreateCart2ResponseForm from(CreateCart2Response response) {
        return new CreateCart2ResponseForm(response.getCartId(), response.getBookId(), response.getBookTitle(), response.getQuantity());
    }
}
