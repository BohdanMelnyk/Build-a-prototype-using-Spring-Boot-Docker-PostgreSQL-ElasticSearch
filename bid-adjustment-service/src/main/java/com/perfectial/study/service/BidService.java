package com.perfectial.study.service;

import com.perfectial.study.domain.Bid;
import java.util.List;

/**
 * Created by bomel on 1/23/2018.
 */
public interface BidService {
    List<Bid> findAllBids();
    Bid addBid(Bid bid);

    Bid addTestBid();
}
