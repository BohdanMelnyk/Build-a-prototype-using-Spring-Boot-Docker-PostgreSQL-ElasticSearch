package com.perfectial.study.service;

import com.perfectial.study.domain.Bid;

/**
 * Created by bomel on 1/24/2018.
 */
public interface SendBidService {
    Bid sendBidToQueue(Bid bid);
}
