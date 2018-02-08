package com.perfectial.study.service;

import com.perfectial.study.domain.BidES;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ESService {

    List<BidES> findByUserName(String userName);
    Page<BidES> findByUserName(String userName, PageRequest pageRequest);

    List<BidES> findByStake(BigDecimal stake);
    Page<BidES> findByStake(BigDecimal stake, PageRequest pageRequest);

    List<BidES> findByAddedDate(LocalDateTime localDateTime);
    Page<BidES> findByAddedDate(LocalDateTime localDateTime, PageRequest pageRequest);

    BidES add(BidES bidES);

}
