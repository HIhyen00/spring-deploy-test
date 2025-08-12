package com.example.demo.book2.service;

import com.example.demo.account.entity.Account;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.book2.entity.Book2;
import com.example.demo.book2.repository.Book2Repository;
import com.example.demo.book2.service.request.ListBookRequest;
import com.example.demo.book2.service.request.RegisterBook2Request;
import com.example.demo.book2.service.response.ListBookResponse;
import com.example.demo.book2.service.response.RegisterBook2Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class Book2ServiceImpl implements Book2Service {

    final private Book2Repository bookRepository;
    final private AccountRepository accountRepository;

    @Override
    public RegisterBook2Response register(RegisterBook2Request request, Long accountId) {
        Optional<Account> maybeAccount = accountRepository.findById(accountId);
        if (maybeAccount.isEmpty()) {
            log.info("요청한 account가 존재하지 않습니다.");
            return null;
        }

        Account account = maybeAccount.get();
        Book2 requestBook2 = request.toBook2(account);

        Book2 savedBook2 = bookRepository.save(requestBook2);
        return RegisterBook2Response.from(savedBook2);
    }

    @Override
    public ListBookResponse list(ListBookRequest request) {
        // PageRequest 생성 ( 시작은 0부터, 화면에 보여줄 땐 0이 요상하니 1이라서 1 빼야함 )
        // PageRequest.of(int page, int size); --> 몇 페이지, 한 페이지에 보여줄 항목 수
        //  ↘ Pageable 인터페이스 구현체 ( page, size, sort )
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getPerPage());

        // 페이지 처리된 항목
        // Account (LAZY 처리) ... 강제로 Account 가져옴 ( Repository 처리 --> JPQL )
        // (반환값) Page<T> : 조회된 결과와 페이징 정보를 함께 담는 객체.. 페이지 단위로 묶인 결과 + 페이징 메타데이터 포함
        //  ↘ getContent():실제 조회된 데이터 리스트 (List<T>), getTotalPages():전체 페이지 수, getTotalElements(): 전체 항목 수
        //    getSize(): 페이지당 항목 수 (자동 ..20), ...
        Page<Book2> paginatedBook = bookRepository.findAllWithAccount(pageRequest);

        return ListBookResponse.from(paginatedBook);
    }
}
