package com.perfectial.study.service;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.repository.BidRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by bomel on 1/23/2018.
 */
@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final SendBidService sendBidService;

    public BidServiceImpl(BidRepository bidRepository, SendBidService sendBidService) {
        this.bidRepository = bidRepository;
        this.sendBidService = sendBidService;
    }

    @Override
    public List<Bid> findAllBids() {
        return bidRepository.findAll();
    }

    @Override
    public Bid addBid(Bid bid) {
        Bid savedBid = bidRepository.save(bid);
        sendBidService.sendBidToQueue(savedBid);
        return savedBid;
    }

    @Override
    public Bid addTestBid() {
        Bid bid = new Bid();
        bid.setId(12l);
        bid.setUserName("Test User");
        bid.setStake(BigDecimal.valueOf(0.123d));
        bid.setAddedDate(LocalDateTime.now());
        bid.setLoggedDate(LocalDateTime. now());
        return addBid(bid);
    }
}
