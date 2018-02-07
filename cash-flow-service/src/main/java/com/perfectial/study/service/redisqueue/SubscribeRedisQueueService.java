package com.perfectial.study.service.redisqueue;

import com.perfectial.study.dto.BidDTO;

import java.util.Collection;

public interface SubscribeRedisQueueService {

     Collection<BidDTO> leftPopAllQueue();
     BidDTO leftPop();
     Collection<BidDTO> getQueue();
}
