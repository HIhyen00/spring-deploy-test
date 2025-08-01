package com.example.demo.order2.controller.response_form;

import com.example.demo.order2.service.response.ListOrdersResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ListOrdersResponseForm {
    final private List<Map<String, Object>> ordersSummaryList;
    final private Integer totalPages;
    final private Long totalElements;

    public static ListOrdersResponseForm from(final ListOrdersResponse response) {
        return response.transformToResponseForm();
    }

}
