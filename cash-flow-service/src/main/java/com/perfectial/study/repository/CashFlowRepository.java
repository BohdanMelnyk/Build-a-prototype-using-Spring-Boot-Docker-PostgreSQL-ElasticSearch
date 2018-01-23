package com.perfectial.study.repository;

import com.perfectial.study.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bomel on 1/23/2018.
 */
//@RepositoryRestResource
public interface CashFlowRepository extends JpaRepository<Bid, Long> {
}
