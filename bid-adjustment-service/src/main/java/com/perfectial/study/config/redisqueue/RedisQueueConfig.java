package com.perfectial.study.config.redisqueue;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.util.BidCustomSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@ComponentScan("com.perfectial.study.*")
public class RedisQueueConfig {

	@Value("${redisqueue.name}")
	String queueName;

	@Value("${redisqueue.host-name}")
	String hostName;

	@Value("${redisqueue.port}")
	int port;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName(hostName);
		jedisConFactory.setPort(port);
		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Bid> redisTemplateRepository() {
		final RedisTemplate<String, Bid> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new BidCustomSerializer());
		return template;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
		return template;
	}

	@Bean
	public RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(jedisConnectionFactory());
		return container;
	}

	@Bean
	public ChannelTopic topic() {
		return new ChannelTopic(queueName);
	}

}
