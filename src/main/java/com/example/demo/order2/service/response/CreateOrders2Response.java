//package com.example.demo.order2.service.response;
//
//import com.example.demo.book2.entity.Book2;
//import com.example.demo.order2.entity.Orders2;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//@Getter
//@RequiredArgsConstructor
//public class CreateOrders2Response {
//    final private Long bookId;
//    final private String bookTitle;
//    final private Long quantity;
//    final private Long price;
//
//    public static CreateOrders2Response from(Orders2 order) {
//        Book2 orderedBook = order.getBook();
//        return new CreateOrders2Response(orderedBook.getId(), orderedBook.getTitle(), order.getQuantity(), order.getPrice());
//    }
//}
