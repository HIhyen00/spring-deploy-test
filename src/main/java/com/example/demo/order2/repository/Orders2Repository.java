package com.example.demo.order2.repository;

import com.example.demo.order2.entity.Orders2;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Orders2Repository extends JpaRepository<Orders2, Long> {
    // @Query는 Custom Query를 구성하는 것으로 JPQL 라고 합니다.
    // ManyToOne의 Lazy Fetch를 하기 때문에 join fetch를 통해 N + 1 문제에 대응
    // 안 하면 너무 많은 검색 퀴리를 유발해서 느려지고 서비스 장애로 이어지게 됨

    // countQuery는 페이지네이션 처리를 위해 전체 개수를 조회하는 역할을 수행
    // join fetch가 count 연산과 호환되지  않기 때문에 별도로 구성해줘야 합니다.
    // countQuery가 없으면 제대로 최적화가 진행되지 않아 성능이 떨어지게 됨
    @Query(
            value =  "select o from Orders2 o join fetch o.account a where a.id= :accountId",
            countQuery = "select count(o) from Orders2 o where o.account.id = :accountId"
    )
    Page<Orders2> findAllByAccountId(@Param("accountId") Long accountId, Pageable pageable);
}
