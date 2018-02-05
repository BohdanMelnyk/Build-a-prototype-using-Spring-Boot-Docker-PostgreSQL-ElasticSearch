package com.perfectial.study.repository;

import com.perfectial.study.domain.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Created by bomel on 1/23/2018.
 */
@RepositoryRestResource
public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {
    Optional<CashFlow> findFirstByUserNameOrderByUpdatedDateDesc(String userName);
    List<CashFlow> findByUserNameOrderByUpdatedDateDesc(String userName);
}