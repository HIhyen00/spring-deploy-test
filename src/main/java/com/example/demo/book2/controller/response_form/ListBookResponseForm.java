package com.example.demo.book2.controller.response_form;

import com.example.demo.book2.service.response.ListBookResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ListBookResponseForm {
    final private List<Map<String, Object>> bookList;
    final private Long totalItems;
    final private Integer totalPages;

    public static ListBookResponseForm from(final ListBookResponse response) {
        List<Map<String, Object>> combinedBookList = response.transformToResponseForm();

        return new ListBookResponseForm(
                combinedBookList,
                response.getTotalItems(),
                response.getTotalPages());
    }
}
