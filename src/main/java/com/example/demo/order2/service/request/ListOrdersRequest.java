package com.example.demo.order2.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListOrdersRequest {
    final private Long accountId;
    final private Integer page;
    final private Integer perPage;


}
