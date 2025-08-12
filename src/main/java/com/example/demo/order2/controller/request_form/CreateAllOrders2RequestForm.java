package com.example.demo.order2.controller.request_form;

import com.example.demo.order2.service.request.CreateAllOrderItemRequest;
import com.example.demo.order2.service.request.CreateAllOrders2Request;
import com.example.demo.order2.service.request.CreateOrderItemRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CreateAllOrders2RequestForm {
    // 여러개를 받을 수 있어야함. 몇 개인지 알 수 없음...  List / Map ... 어떤 형태로 입력..? 여러 책 ?? cart ??
    // account에 대한 정보와 여러 order에 대한 정보가 같이 들어옴 >> 나눠서 받음 ...
    // request와 requestForm을 나누는 이유 :
    // 도메인을 나눔으로써 각 entity? 에만 집중할 수 있음, controller가 지저분해짐, 추가사항을 requestForm에 사용 ..
    final private List<CreateOrderItemRequestForm> orderItems;

    public CreateAllOrders2Request toCreateAllOrders2Request(Long accountId) {
        return new CreateAllOrders2Request(accountId);
    }

    public CreateAllOrderItemRequest toCreateAllOrderItemRequest() {
        // ordersItem을 리스트
        // 이러한 List 들은 stream()을 사용할 수 있음
        // map을 통해 내부에 있는 정보들을 전부 순회 처리할 수 있음.
        // 순회하면서 mapped에 있는 요소 하나하나는 CreateOrderItemRequest 타입이므로
        // CreateOrderItemRequest 타입들이 사용할 수 있는
        // toCreateOrderItemRequest()를 통해 RequestForm 타입을 Request로 변환
        // collect()를 통해 List 타입으로 만들어줌
        // for 문 보다 속도가 빠름!!!!
        List<CreateOrderItemRequest> mapped = orderItems.stream()
                .map(CreateOrderItemRequestForm::toCreateOrderItemRequest)
                .collect(Collectors.toList());
        /*
        List<CreateOrderItemRequest> mapped = new ArrayList<>();

        for (int i = 0; i < mapped.size(); i++) {
            CreateOrderItemRequestForm mappedRequestForm = mapped.get(i);
            CreateOrderItemRequest mappedRequest = mappedRequestForm.toCreateOrderItemRequest();

            mapped.put(mappedRequest);
        }
        */
        // CreateOrderItemRequest를 (List 통해) CreateAllOrderItemRequest로!!
        return new CreateAllOrderItemRequest(mapped);
    }

}
