package com.perfectial.study.service;

import com.perfectial.study.domain.Bid;

/**
 * Created by bomel on 1/23/2018.
 */
public interface BidService {
    Iterable<Bid> findAllBids();
    Bid addBid(Bid bid);
}
