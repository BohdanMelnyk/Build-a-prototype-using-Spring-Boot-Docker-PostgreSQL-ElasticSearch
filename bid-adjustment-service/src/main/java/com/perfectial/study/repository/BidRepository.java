package com.perfectial.study.repository;

import com.perfectial.study.domain.Bid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by bomel on 1/23/2018.
 */
@RepositoryRestResource
public interface BidRepository extends JpaRepository<Bid, Long> {

//    List<Bid> findByUserName(String userName);
//
//    List<Bid> findByStake(BigDecimal stake);
//
//    List<Bid> findByAddedDate(LocalDateTime addedDate);
}
