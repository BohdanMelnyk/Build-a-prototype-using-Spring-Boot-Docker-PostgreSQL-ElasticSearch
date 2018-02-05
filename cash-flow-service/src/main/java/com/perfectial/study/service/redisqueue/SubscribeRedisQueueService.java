package com.perfectial.study.service.redisqueue;

import com.perfectial.study.dto.BidDTO;

import java.util.Collection;

/**
 * Created by bomel on 1/25/2018.
 */
public interface SubscribeRedisQueueService {

     Collection<BidDTO> leftPopAllQueue();
     BidDTO leftPop();
     Collection<BidDTO> getQueue();
}
