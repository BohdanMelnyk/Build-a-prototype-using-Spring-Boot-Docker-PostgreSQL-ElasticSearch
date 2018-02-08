package com.perfectial.study.service;

import com.perfectial.study.domain.Bid;

import java.util.List;

public interface BidService {
    List<Bid> findAllBids();
    Bid add(Bid bid);
    List<Bid> add(List<Bid> bid);
    Bid addTestBid();



}
