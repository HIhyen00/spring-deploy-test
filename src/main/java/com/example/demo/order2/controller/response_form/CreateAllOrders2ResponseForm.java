package com.example.demo.order2.controller.response_form;

import com.example.demo.order2.service.response.CreateOrderItemResponse;
import com.example.demo.order2.service.response.CreateAllOrders2Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CreateAllOrders2ResponseForm {
    final private Long ordersId;
    final private Integer itemCount;
    final private List<CreateOrderItemResponse> itemList;

    public static CreateAllOrders2ResponseForm from(final CreateAllOrders2Response response){
        return new CreateAllOrders2ResponseForm(response.getOrdersId(), response.getItemCount(), response.getItemList());
    }
}
