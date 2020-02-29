package com.quintifi.kafkaeventsutil.service;

import org.springframework.scheduling.annotation.Async;

/**
 * 
 * @author nchopra
 *
 */
public interface KafkaAsyncProducerService {

	@Async
	public <T> void sendMessage(String topic, String key, String message) throws Exception;

	@Async
	public <T> void sendMessage(String topic, String message) throws Exception;

	@Async
	public <T> void sendMessage(String topic, Integer partition, String key, String message) throws Exception;

	@Async
	public <T> void sendMessage(String topic, Integer partition, String key, Long timestamp, String message)
			throws Exception;
}
