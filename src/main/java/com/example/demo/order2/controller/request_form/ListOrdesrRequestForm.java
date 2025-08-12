package com.example.demo.order2.controller.request_form;

import com.example.demo.order2.service.request.ListOrdersRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListOrdesrRequestForm {
    final private Integer page;
    final private Integer perPage;

    public ListOrdersRequest toListOrdersRequest(Long accountId) {
        return new ListOrdersRequest(accountId, page, perPage);
    }
}
