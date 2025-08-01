package com.example.demo.book2.controller.request_form;

import com.example.demo.book2.service.request.ListBookRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ListBookRequestForm {
    // Wrapper를 사용해야 getter, setter를 원활하게 사용할 수 있음 (int로 해도 되긴 함)
    // null 값 처리 및 데이터 유효성 검증을 용이하게 하기 위함 (데이터 모델의 명확성, API 응답 데이터 형식의 일관성)
    final private Integer page;
    final private Integer perPage;

    public ListBookRequest toListBookRequest() {
        return new ListBookRequest(page, perPage);
    }
}
