package com.example.demo.order2.service.request;

import com.example.demo.book2.entity.Book2;
import com.example.demo.order2.entity.OrderItem;
import com.example.demo.order2.entity.Orders2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CreateAllOrderItemRequest {
    final private List<CreateOrderItemRequest> orderItem;

    // 기존의 경우 RequestForm -> Request: toRequest()
    // Request -> Entity -> toEntity()
    // Request -> Entity는  동일하나 리스트 형태로 바꿈 -> toEntityList()
    public List<OrderItem> toOrderItemList(List<Book2> bookList, Orders2 orders) {
        // Map 객체를 구성하였음
        // KEY : bookId
        // value : Book Entity
        // bookList를 stream()으로 구성
        // Collectors.toMap() 사용하므로 HashMap을 구성하겠다는 의미
        // Book::getId를 사용해서 bookId를 확보
        // Function.identity() 객체 자신을 가져옵니다.
        // stream()을 사용하여 자체 for 루프라는 것을 상기해야함
        Map<Long, Book2> bookMap = bookList.stream().collect(Collectors.toMap(Book2::getId, Function.identity()));
        // ordersItem는 CreateOrderItemRequest의 List
        // map을 사용해서 가각의 요소를 '->' 내부의 동작으로 처리하게 됨
        // return을 보면 toOrderItem이 실행되므로
        // OrderItem Entity를 리스트 형태로 구성해주고 있음
        return orderItem.stream().map( item -> {
            // bookMap을 통해 책의 id(bookId) 값으로 Book을 찾음.
            Book2 book = bookMap.get(item.getBookId());
            if (book == null) {
                throw new IllegalArgumentException(item.getBookId() + "이라는 책은 존재하지 않습니다.");
            }

            // id에 해당하는 Book이 null이 아니면
            // OrderItem에 Book Entity와 Orders Entity를 배치함
            // stream을 통해 이뤄지므로 List<OrderItem> 이 만들어지게 됨
            // book과 orders 정보를 받는 이유는 CreateOrderItemRequest 에서 toOrderItem을 하는데, OderItem을 만들 때 필요한 quantity와 price가 Request에 존재
            return item.toOrderItem(book, orders);
        }).collect(Collectors.toList());
    }
}
