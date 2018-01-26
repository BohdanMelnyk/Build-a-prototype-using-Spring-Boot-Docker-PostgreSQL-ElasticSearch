package com.perfectial.study.service;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.service.redisqueue.PublishRedisQueueService;
import com.perfectial.study.service.redisqueue.PublishRedisQueueServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class SendBidServiceImpl implements SendBidService {

    private final PublishRedisQueueService publishRedisQueueService;

    public SendBidServiceImpl(PublishRedisQueueServiceImpl publishRedisQueueService) {
        this.publishRedisQueueService = publishRedisQueueService;
    }

    @Override
    public Bid sendBidToQueue(Bid bid) {
        if(bid!= null){
            publishRedisQueueService.rightPush(bid);
            publishRedisQueueService.sendMessage();
        }
        return bid;
    }
}
