package com.example.demo.order2.repository;

import com.example.demo.order2.entity.OrderItem;
import com.example.demo.order2.entity.Orders2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query(value =  "select o from OrderItem o join fetch o.book b")
    List<OrderItem> findByOrdersIn(Collection<Orders2> orders);
}
