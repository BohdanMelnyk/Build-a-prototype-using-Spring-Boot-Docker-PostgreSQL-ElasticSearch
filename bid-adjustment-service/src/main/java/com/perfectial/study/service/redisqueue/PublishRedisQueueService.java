package com.perfectial.study.service.redisqueue;

import com.perfectial.study.domain.Bid;

/**
 * Created by bomel on 1/25/2018.
 */
public interface PublishRedisQueueService {

    void sendMessage();
    void rightPush(Bid bid);
}
