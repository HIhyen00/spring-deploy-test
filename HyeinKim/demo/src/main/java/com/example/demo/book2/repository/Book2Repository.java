package com.example.demo.book2.repository;

import com.example.demo.book2.entity.Book2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Book2Repository extends JpaRepository<Book2, Long> {
    @Query("select b from Book2 b join fetch b.account")
    Page<Book2> findAllWithAccount(PageRequest pageRequest);
}
