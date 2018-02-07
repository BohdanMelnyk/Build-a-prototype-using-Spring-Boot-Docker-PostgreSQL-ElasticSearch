package com.perfectial.study.util;

import com.perfectial.study.dto.BidDTO;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

@Component
public class BidDTOCustomSerializer implements RedisSerializer<BidDTO> {

	private final ObjectMapper objectMapper;

	public BidDTOCustomSerializer() {
		super();
		this.objectMapper = new ObjectMapper().enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
	}

	@Override
	public byte[] serialize(BidDTO bid) throws SerializationException {
		try {
			return objectMapper.writeValueAsBytes(bid);
		} catch (JsonProcessingException e) {
			throw new SerializationException(e.getMessage(), e);
		}
	}

	@Override
	public BidDTO deserialize(byte[] bytes) throws SerializationException {
		try {
			return objectMapper.readValue(bytes, BidDTO.class);
		} catch (Exception e) {
			throw new SerializationException(e.getMessage(), e);
		}
	}
}
