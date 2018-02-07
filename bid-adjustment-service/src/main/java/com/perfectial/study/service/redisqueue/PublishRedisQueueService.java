package com.perfectial.study.service.redisqueue;

import com.perfectial.study.dto.BidDTO;

public interface PublishRedisQueueService {

    void sendMessage();
    void rightPush(BidDTO bidDTO);
}
