package com.quintifi.kafkaeventsutil.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.quintifi.kafkaeventsutil.service.KafkaAsyncFireForgetProducerService;

/**
 * 
 * @author nchopra
 *
 */
@Service
public class KafkaAsyncFireForgetProducerServiceImpl implements KafkaAsyncFireForgetProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private static final Logger logger = LoggerFactory.getLogger(KafkaAsyncFireForgetProducerServiceImpl.class);

	@Async
	public <T> void sendMessage(String topic, String key, String message) throws Exception {

		try {
			kafkaTemplate.send(topic, key, message);
		} catch (Exception e) {
			logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}

	@Async
	public <T> void sendMessage(String topic, String message) throws Exception {

		try {
			kafkaTemplate.send(topic, message);
		} catch (Exception e) {
			logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}

	@Async
	public <T> void sendMessage(String topic, Integer partition, String key, String message) throws Exception {

		try {
			kafkaTemplate.send(topic, partition, key, message);
		} catch (Exception e) {
			logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}

	@Async
	public <T> void sendMessage(String topic, Integer partition, String key, Long timestamp, String message)
			throws Exception {

		try {
			kafkaTemplate.send(topic, partition, timestamp, key, message);
		} catch (Exception e) {
			logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}
}
