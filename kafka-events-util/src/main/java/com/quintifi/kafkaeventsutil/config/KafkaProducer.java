package com.quintifi.kafkaeventsutil.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * 
 * @author nchopra
 *
 */
@Service
public class KafkaProducer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	public void sendMessage(String topic, String key, String message, KafkaTemplate<String, String> kafkaTemplate)
			throws Exception {

		try {
			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message);
			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
				@Override
				public void onSuccess(SendResult<String, String> result) {
				}

				@Override
				public void onFailure(Throwable ex) {
					logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), ex);
				}
			});
		} catch (Exception e) {
			logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}

	public void sendMessage(String topic, String message, KafkaTemplate<String, String> kafkaTemplate)
			throws Exception {

		try {
			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
				@Override
				public void onSuccess(SendResult<String, String> result) {
				}

				@Override
				public void onFailure(Throwable ex) {
					logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), ex);
				}
			});
		} catch (Exception e) {
			logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}

	public void sendMessage(String topic, Integer partition, String key, String message,
			KafkaTemplate<String, String> kafkaTemplate) throws Exception {

		try {
			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, partition, key, message);
			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
				@Override
				public void onSuccess(SendResult<String, String> result) {
				}

				@Override
				public void onFailure(Throwable ex) {
					logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), ex);
				}
			});
		} catch (Exception e) {
			logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}

	public void sendMessage(String topic, Integer partition, String key, Long timestamp, String message,
			KafkaTemplate<String, String> kafkaTemplate) throws Exception {

		try {
			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, partition, timestamp, key,
					message);
			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
				@Override
				public void onSuccess(SendResult<String, String> result) {
				}

				@Override
				public void onFailure(Throwable ex) {
					logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), ex);
				}
			});
		} catch (Exception e) {
			logger.error(String.format("Failed to send [ %s ] to topic %s ", message, topic), e);
			throw e;
		}
	}
}
