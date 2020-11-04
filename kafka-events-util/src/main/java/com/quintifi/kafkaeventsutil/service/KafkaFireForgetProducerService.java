package com.quintifi.kafkaeventsutil.service;

/**
 * 
 * @author nchopra
 *
 */
public interface KafkaFireForgetProducerService {

	public <T> void sendMessage(String topic, String key, String message) throws Exception;

	public <T> void sendMessage(String topic, String message) throws Exception;

	public <T> void sendMessage(String topic, Integer partition, String key, String message) throws Exception;

	public <T> void sendMessage(String topic, Integer partition, String key, Long timestamp, String message)
			throws Exception;
}
