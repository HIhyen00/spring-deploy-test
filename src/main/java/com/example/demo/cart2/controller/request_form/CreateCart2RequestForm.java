package com.example.demo.cart2.controller.request_form;

import com.example.demo.cart2.service.request.CreateCart2Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCart2RequestForm {
    final private Long bookId;
    final private Long quantity;

    public CreateCart2Request toCreateCart2Request() {
        return new CreateCart2Request(bookId, quantity);
    }
}
