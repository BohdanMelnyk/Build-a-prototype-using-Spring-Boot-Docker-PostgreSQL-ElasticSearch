package com.perfectial.study.repository.redisqueue;

import com.perfectial.study.domain.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class BidRedisQueueRepositoryImpl implements BidRedisQueueRepository<String, Bid> {

	@Autowired
	private RedisTemplate<String, Bid> redisTemplate;


	@Override
	public void push(String key, Bid value, boolean right) {
		if (right) {
			redisTemplate.opsForList().rightPush(key, value);
		} else {
			redisTemplate.opsForList().leftPush(key, value);
		}
	}

	@Override
	public void multiAdd(String key, Collection<Bid> values, boolean right) {
		for (Bid value : values) {
			push(key, value, right);
		}
	}

	@Override
	public Collection<Bid> get(String key) {
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	@Override
	public Bid pop(String key, boolean right) {
		Bid value;
		if (right) {
			value = redisTemplate.opsForList().rightPop(key);
		} else {
			value = redisTemplate.opsForList().leftPop(key);
		}
		return value;
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public void trim(String key, int start, int end) {
		redisTemplate.opsForList().trim(key, start, end);
	}

	@Override
	public Long size(String key) {
		return redisTemplate.opsForList().size(key);
	}

	@Override
	public Collection<Bid> popAll(String key) {
		Collection<Bid> bids = new ArrayList<>();
		Bid bid = pop(key, false);
		while (bid != null) {
			bids.add(bid);
			bid = pop(key, false);
		}
		return bids;
	}
}