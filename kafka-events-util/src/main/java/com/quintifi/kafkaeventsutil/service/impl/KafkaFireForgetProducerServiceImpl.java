package com.quintifi.kafkaeventsutil.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.quintifi.kafkaeventsutil.service.KafkaFireForgetProducerService;

/**
 * 
 * @author nchopra
 *
 */
@Service
public class KafkaFireForgetProducerServiceImpl implements KafkaFireForgetProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaFireForgetProducerServiceImpl.class);

	public <T> void sendMessage(String topic, String key, String message) throws Exception {

		try {
			kafkaTemplate.send(topic, key, message);
		} catch (Exception e) {
			LOGGER.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}

	public <T> void sendMessage(String topic, String message) throws Exception {

		try {
			kafkaTemplate.send(topic, message);
		} catch (Exception e) {
			LOGGER.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}

	public <T> void sendMessage(String topic, Integer partition, String key, String message) throws Exception {

		try {
			kafkaTemplate.send(topic, partition, key, message);
		} catch (Exception e) {
			LOGGER.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}

	public <T> void sendMessage(String topic, Integer partition, String key, Long timestamp, String message)
			throws Exception {

		try {
			kafkaTemplate.send(topic, partition, timestamp, key, message);
		} catch (Exception e) {
			LOGGER.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}
}
