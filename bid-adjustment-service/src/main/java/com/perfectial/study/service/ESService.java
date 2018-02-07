package com.perfectial.study.service;

import com.perfectial.study.domain.Bid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ESService {

    List<Bid> findByUserName(String userName);
    Page<Bid> findByUserName(String userName, PageRequest pageRequest);

    List<Bid> findByStake(BigDecimal stake);
    Page<Bid> findByStake(BigDecimal stake, PageRequest pageRequest);

    List<Bid> findByAddedDate(LocalDateTime localDateTime);
    Page<Bid> findByAddedDate(LocalDateTime localDateTime, PageRequest pageRequest);

}
