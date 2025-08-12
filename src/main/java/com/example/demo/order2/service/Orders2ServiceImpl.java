package com.example.demo.order2.service;

import com.example.demo.account.entity.Account;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.book2.entity.Book2;
import com.example.demo.book2.repository.Book2Repository;
import com.example.demo.order2.entity.OrderItem;
import com.example.demo.order2.entity.Orders2;
import com.example.demo.order2.repository.OrderItemRepository;
import com.example.demo.order2.repository.Orders2Repository;
import com.example.demo.order2.service.request.CreateAllOrderItemRequest;
import com.example.demo.order2.service.request.CreateAllOrders2Request;
import com.example.demo.order2.service.request.CreateOrderItemRequest;
import com.example.demo.order2.service.request.ListOrdersRequest;
import com.example.demo.order2.service.response.CreateAllOrders2Response;
import com.example.demo.order2.service.response.ListOrdersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class Orders2ServiceImpl implements Orders2Service {

    final private Orders2Repository ordersRepository;
    final private OrderItemRepository orderItemRepository;
    final private AccountRepository accountRepository;
    final private Book2Repository bookRepository;

    @Override
    @Transactional
    public CreateAllOrders2Response createAll(CreateAllOrders2Request ordersRequest, CreateAllOrderItemRequest orderItemsRequest) {

        // accountId로 Account 찾기
        // 사용자가 유효한지 여부 파악
        Long accountId = ordersRequest.getAccountId();
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("존재하지 않는 사람입니다"));

        // Orders의 경우 사용자가 유효하니 즉시 생성
        Orders2 orders = ordersRequest.toOrders(account);
        Orders2 savedOrders = ordersRepository.save(orders);

        // 존재하는 책인지 검사하기 위함!***

        // 실제 orderItemRequest 에는 {id ... }, {id ... }, {id ... } 형식으로 배치될 것이므로
        // id만 따로 빼놓은 리스트를 구성하기 위해 아래와 같은 작업을 진행
        // getBookId를 통해 bookId 값만 뽑아오고 List로 만듦
        List<Long> bookIdList = orderItemsRequest.getOrderItem().stream()
                .map(CreateOrderItemRequest:: getBookId)
                .distinct()
                .collect(Collectors.toList());

        // findAll -> 조금 더 확장된 개념인 findAllById 가 나타남
        // findAll과 하는 일은 동일하지만 조건이 추가된 형식
        // 조건은 bookIdList에 해당하는 모든 정보를 검색 ( bookId (pk))
        List<Book2> bookList = bookRepository.findAllById(bookIdList);
        if(bookList.size() != bookIdList.size()) {
            throw new IllegalArgumentException("존재하지 않는 책이 포함되었습니다.");
        }

        // Orders는 주문 그 자체이므로 1개일 수 밖에 없음
        // 그러나 주문에 포함된 항목들은 다수 일 수 있음
        // 그렇기에 OrderItem은 리스트 형식으로 구성됨
        // OrderItem 리스트를 저장하기 위해 save가 아닌 saveAll로 bulk 연산함 (다중처리)
        // for 루프 돌면서 save 하는 것과 saveAll의 차이는 결국 데이터 무결성을 보장하기 위함
        // 데이터 무결성이라는 것이 1+1=2가 나와야 하는데
        // 1+1=1이 나오는 괴현상을 나타나지 않게 해주는 것임
        // 좀 더 전문 용어로는 Lock을 걸어서 데이터를 안전하게 보호한다 보면 되는데
        // 그냥 다수의 데이터를 처리할 때는 무조건 saveAll 사용하나다 생각하는 것이 편함
        // 다수의 데이터 저장 시,loop로 저장 xx (비추) / thread .. 데이터가 깨질 수 있음
        // OrderItemList로 바꿔 줌 (order와 bookList의 유효성을 각 각 검사하고 response로 모아 줌)***
        List<OrderItem> orderItemList = orderItemsRequest.toOrderItemList(bookList, savedOrders);
        List<OrderItem> savedOrderItemList = orderItemRepository.saveAll(orderItemList);

        return CreateAllOrders2Response.from(orders, savedOrderItemList);
    }

    @Override
    public ListOrdersResponse list(ListOrdersRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getPerPage(), Sort.by("createdAt").descending());

        Long accountId = request.getAccountId();
        Page<Orders2> pagedOrders = ordersRepository.findAllByAccountId(accountId, pageable);
        List<Orders2> pagedOrdersList = pagedOrders.getContent();

        List<OrderItem> pagedOrderItemList = orderItemRepository.findByOrdersIn(pagedOrdersList);

        return ListOrdersResponse.from(pagedOrdersList, pagedOrderItemList, pagedOrders.getTotalPages(), pagedOrders.getTotalElements());
    }
}
