package com.perfectial.study.service.redisqueue;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.repository.redisqueue.BidRedisQueueRepository;

@Service
public class SubscribeRedisQueueServiceImpl implements SubscribeRedisQueueService{

	@Value("${redisqueue.name}")
	String queueName;

	private BidRedisQueueRepository<String, Bid> bidQueue;

	public SubscribeRedisQueueServiceImpl(BidRedisQueueRepository<String, Bid> bidQueue) {
		super();
		this.bidQueue = bidQueue;
	}

	@Override
	public Collection<Bid> leftPopAllQueue() {
		return bidQueue.popAll(queueName);
	}

	@Override
	public Bid leftPop() {
		return bidQueue.pop(queueName, false);

	}

	@Override
	public Collection<Bid> getQueue() {
		return bidQueue.get(queueName);
	}
}
