package com.perfectial.study.config.redisqueue;


public interface RedisQueueMessagePublisher {

	void publish(final String message);

}
