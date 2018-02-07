package com.perfectial.study.service.redisqueue;

import com.perfectial.study.dto.BidDTO;

import java.util.Collection;

public interface ProcessingBidFromQueueService {

    void processAllBidsFromQueue();

    void processBidFromQueue();

    void process(Collection<BidDTO> bids);

    void process(BidDTO bid);
}
