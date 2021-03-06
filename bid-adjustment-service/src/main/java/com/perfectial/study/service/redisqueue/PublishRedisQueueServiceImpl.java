package com.perfectial.study.service.redisqueue;

import com.perfectial.study.config.redisqueue.RedisQueueMessagePublisher;
import com.perfectial.study.dto.BidDTO;
import com.perfectial.study.repository.redisqueue.RedisQueueRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PublishRedisQueueServiceImpl implements PublishRedisQueueService {

	@Value("${redisqueue.name}")
	String queueName;

	private final RedisQueueMessagePublisher redisMessagePublisher;

	private final RedisQueueRepository<String, BidDTO> trafficQueue;


	public PublishRedisQueueServiceImpl(RedisQueueMessagePublisher redisMessagePublisher, RedisQueueRepository<String, BidDTO> trafficQueue) {
		super();
		this.redisMessagePublisher = redisMessagePublisher;
		this.trafficQueue = trafficQueue;
	}

	public void sendMessage() {
		redisMessagePublisher.publish("The bidQueue is not empty at " + LocalDateTime.now());
	}


	public void rightPush(BidDTO bid) {
		trafficQueue.push(queueName, bid, true);
	}
}
