package com.quintifi.kafkaeventsutil.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author nchopra
 *
 */
public abstract class KafkaConsumerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	@Autowired
	private KafkaProducerService kafkaProducer;

	@Value("${kafka.error.topic}")
	private String errorTopic;

	@Value("${kafka.consumer.retry:2}")
	private int retryCount;

	public abstract boolean execute(String msg);

	public final void processMessage(Map<String, Map<Integer, List<byte[]>>> msgs) {
		for (Map.Entry<String, Map<Integer, List<byte[]>>> entry : msgs.entrySet()) {

			logger.debug("Topic:" + entry.getKey());

			ConcurrentHashMap<Integer, List<byte[]>> messages = (ConcurrentHashMap<Integer, List<byte[]>>) entry
					.getValue();
			Collection<List<byte[]>> values = messages.values();

			for (Iterator<List<byte[]>> iterator = values.iterator(); iterator.hasNext();) {
				List<byte[]> list = iterator.next();
				for (byte[] object : list) {
					String message = new String(object);
					logger.debug("Message: " + message);

					/*
					 * Executing implementation specific work here. This retries to execute the
					 * message for fixed time after which it adds the message to the error channel.
					 */
					int execCount = 0;
					do {
						try {
							if (execute(message)) {
								break;
							}
						} catch (Exception e) {
							if (execCount == retryCount) {
								logger.error(
										"Exception while consuming kafka message after " + retryCount + " retries.", e);
							}
						}
					} while (++execCount <= retryCount);

					if (execCount == 0 || execCount <= retryCount) {
						logger.debug("Successfully consumed message from kafka after {} tries.", execCount);
					} else {
						logger.debug(
								"Failed to consume message from kafka after {} tries, sending message to error topic.",
								execCount);
						try {
							kafkaProducer.sendMessage(errorTopic, message);
						} catch (Exception e) {
							logger.error("Error while sending data to kafka error topic:", e);
							logger.error("Failed to send the message: ", message);
						}
					}

				}
			}
		}

	}

}
