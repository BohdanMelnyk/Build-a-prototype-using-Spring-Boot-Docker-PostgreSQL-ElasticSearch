package com.perfectial.study.service;

import com.perfectial.study.domain.Bid;

import java.util.List;

public interface BidService {
    List<Bid> findAllBids();
    Bid addBid(Bid bid);
    Bid addTestBid();



}
