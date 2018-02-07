package com.perfectial.study.service;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.repository.BidRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ESServiceImpl implements ESService {
    private final BidRepository bidRepository;

    public ESServiceImpl(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public List<Bid> findByUserName(String userName) {
        return bidRepository.findByUserName(userName);
    }

    @Override
    public Page<Bid> findByUserName(String userName, PageRequest pageRequest) {
        return bidRepository.findByUserName(userName, pageRequest);
    }

    @Override
    public List<Bid> findByStake(BigDecimal stake) {
        return bidRepository.findByStake(stake);
    }

    @Override
    public Page<Bid> findByStake(BigDecimal stake, PageRequest pageRequest) {
        return bidRepository.findByStake(stake, pageRequest);
    }

    @Override
    public List<Bid> findByAddedDate(LocalDateTime localDateTime) {
        return bidRepository.findByAddedDate(localDateTime);
    }

    @Override
    public Page<Bid> findByAddedDate(LocalDateTime localDateTime, PageRequest pageRequest) {
        return bidRepository.findByAddedDate(localDateTime, pageRequest);
    }
}
