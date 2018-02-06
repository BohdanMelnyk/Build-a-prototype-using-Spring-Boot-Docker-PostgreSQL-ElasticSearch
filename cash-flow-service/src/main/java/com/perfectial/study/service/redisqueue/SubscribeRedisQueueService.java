package com.perfectial.study.service.redisqueue;

import com.perfectial.study.domain.Bid;

import java.util.Collection;

/**
 * Created by bomel on 1/25/2018.
 */
public interface SubscribeRedisQueueService {

     Collection<Bid> leftPopAllQueue();
     Bid leftPop();
     Collection<Bid> getQueue();
}
