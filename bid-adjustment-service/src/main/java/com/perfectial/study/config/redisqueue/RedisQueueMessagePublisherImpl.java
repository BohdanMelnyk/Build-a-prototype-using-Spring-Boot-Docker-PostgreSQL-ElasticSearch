package com.perfectial.study.config.redisqueue;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisQueueMessagePublisherImpl implements RedisQueueMessagePublisher {

	private final RedisTemplate<String, Object> redisTemplate;
	private final ChannelTopic topic;

	public RedisQueueMessagePublisherImpl(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
		this.redisTemplate = redisTemplate;
		this.topic = topic;
	}

	public void publish(String message) {

		redisTemplate.convertAndSend(topic.getTopic(), message);
	}
}