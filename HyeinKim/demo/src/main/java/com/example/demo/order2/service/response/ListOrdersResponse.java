package com.example.demo.order2.service.response;

import com.example.demo.order2.controller.response_form.ListOrdersResponseForm;
import com.example.demo.order2.entity.OrderItem;
import com.example.demo.order2.entity.Orders2;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ListOrdersResponse {
    final private List<Orders2> pagedOrdersList;
    final private List<OrderItem> pagedOrderItemList;
    final private Integer totalPages;
        final private Long totalElements;

    public ListOrdersResponseForm transformToResponseForm() {
        List<Map<String, Object>> ordersSummaryList = pagedOrdersList.stream()
                .map(orders -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("orderId", orders.getId());
                    map.put("createdAt", orders.getCreatedAt());

                    List<OrderItem> itemsForOrder = pagedOrderItemList.stream()
                            .filter(orderItem -> orderItem.getOrders().getId().equals(orders.getId()))
                            .collect(Collectors.toList());

                    String bookNameList = itemsForOrder.stream()
                            .map(item -> item.getBook().getTitle())
                            .collect(Collectors.joining(","));

                    if (bookNameList.length() > 20) {
                        bookNameList = bookNameList.substring(0, 20) + ".....";
                    }
                    map.put("bookName", bookNameList);

                    Long totalPrice = itemsForOrder.stream()
                            .mapToLong(item -> item.getPrice() * item.getQuantity())
                            .sum();
                    map.put("totalPrice", totalPrice);
                    return map;
                })
                .collect(Collectors.toList());

        return new ListOrdersResponseForm(
                ordersSummaryList, totalPages, totalElements
        );
    }

    public static ListOrdersResponse from(List<Orders2> pagedOrdersList, List<OrderItem> pagedOrderItemList, Integer totalPages, Long totalElements) {
        return new ListOrdersResponse(pagedOrdersList, pagedOrderItemList, totalPages, totalElements);
    }
}
