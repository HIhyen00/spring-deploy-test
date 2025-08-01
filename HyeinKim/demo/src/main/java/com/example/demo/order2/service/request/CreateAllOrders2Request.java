package com.example.demo.order2.service.request;

import com.example.demo.account.entity.Account;
import com.example.demo.book2.entity.Book2;
import com.example.demo.order2.entity.OrderItem;
import com.example.demo.order2.entity.Orders2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateAllOrders2Request {
    final private Long accountId;

    public Orders2 toOrders(Account account) {
        return new Orders2(account);
    }
}
