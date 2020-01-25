package com.quintifi.kafkaeventsutil.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author nchopra
 *
 */
@Component
public class JsonHelper {
	@Autowired
	private ObjectMapper objectMapper;

	public <T> String toJson(T toMarshal) {
		try {
			return objectMapper.writeValueAsString(toMarshal);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	public <T> T fromJson(String json, Class<T> clazz) throws Exception {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean isJSONValid(String jsonInString) {
		try {
			objectMapper.readTree(jsonInString);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
