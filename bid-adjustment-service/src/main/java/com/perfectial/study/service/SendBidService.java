package com.perfectial.study.service;

import com.perfectial.study.domain.Bid;

public interface SendBidService {
    Bid sendBidToQueue(Bid bid);
}
