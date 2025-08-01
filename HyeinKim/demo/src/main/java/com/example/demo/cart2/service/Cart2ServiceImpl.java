package com.example.demo.cart2.service;

import com.example.demo.account.entity.Account;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.book2.entity.Book2;
import com.example.demo.book2.repository.Book2Repository;
import com.example.demo.cart2.entity.Cart2;
import com.example.demo.cart2.repository.Cart2Repository;
import com.example.demo.cart2.service.request.CreateCart2Request;
import com.example.demo.cart2.service.response.CreateCart2Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Cart2ServiceImpl implements Cart2Service {

    final private AccountRepository accountRepository;
    final private Book2Repository book2Repository;
    final private Cart2Repository cart2Repository;

    @Override
    public CreateCart2Response create(CreateCart2Request request, Long accountId) {

        Long bookId = request.getBookId();
        Optional<Book2> maybeBook = book2Repository.findById(bookId);
        if (maybeBook.isEmpty()) {
            return null;
        }
        Book2 requestedBook = maybeBook.get();

        Optional<Account> maybeAccount = accountRepository.findById(accountId);
        if (maybeAccount.isEmpty()) {
            return null;
        }
        Account requestedAccount = maybeAccount.get();

        Cart2 requestCart = request.toCart(requestedBook, requestedAccount);
        Cart2 cart = cart2Repository.save(requestCart);
        return CreateCart2Response.from(cart);
    }
}
