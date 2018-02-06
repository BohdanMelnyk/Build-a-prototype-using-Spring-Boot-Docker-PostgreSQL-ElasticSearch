package com.perfectial.study.service.redisqueue;

import com.perfectial.study.domain.Bid;

import java.util.Collection;

/**
 * Created by bomel on 1/25/2018.
 */
public interface ProcessingBidFromQueueService {

    void processAllBidsFromQueue();

    void processBidFromQueue();

    void process(Collection<Bid> bids);

    void process(Bid bid);
}
