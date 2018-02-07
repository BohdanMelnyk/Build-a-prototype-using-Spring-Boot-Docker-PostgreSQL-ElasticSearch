package com.perfectial.study.repository;

import com.perfectial.study.domain.Bid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource
public interface BidRepository extends ElasticsearchRepository<Bid, Long> {
//public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findAll();

    List<Bid> findByUserName(String userName);
    Page<Bid> findByUserName(String userName, Pageable pageable);

    List<Bid> findByStake(BigDecimal stake);
    Page<Bid> findByStake(BigDecimal stake, Pageable pageable);

    List<Bid> findByAddedDate(LocalDateTime localDateTime);
    Page<Bid> findByAddedDate(LocalDateTime localDateTime, Pageable pageable);

}
