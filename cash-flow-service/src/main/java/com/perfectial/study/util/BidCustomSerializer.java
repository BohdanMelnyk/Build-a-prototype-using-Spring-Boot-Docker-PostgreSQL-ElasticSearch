package com.perfectial.study.util;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.perfectial.study.domain.Bid;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

@Component
public class BidCustomSerializer implements RedisSerializer<Bid> {

	private final ObjectMapper objectMapper;

	public BidCustomSerializer() {
		super();
		this.objectMapper = new ObjectMapper().enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
	}

	@Override
	public byte[] serialize(Bid t) throws SerializationException {
		try {
			return objectMapper.writeValueAsBytes(t);
		} catch (JsonProcessingException e) {
			throw new SerializationException(e.getMessage(), e);
		}
	}

	@Override
	public Bid deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null) {
			return null;
		}

		try {
			return objectMapper.readValue(bytes, Bid.class);
		} catch (Exception e) {
			throw new SerializationException(e.getMessage(), e);
		}
	}
}
