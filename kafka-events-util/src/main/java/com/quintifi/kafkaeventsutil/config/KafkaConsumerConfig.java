package com.quintifi.kafkaeventsutil.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

/**
 * @author nchopra
 */

@Configuration
public class KafkaConsumerConfig {

	@Value("${kafka.broker.address}")
	private String brokerAddress;

	@Value("${kafka.consumer.concurrency}")
	private Integer consumerConcurrency;

	@Value("${kafka.consume.group}")
	private String consumerGroup;

	@Value("${kafka.consumer.batch.size}")
	private String consumerBatchSize;

	@Value("${poll.timeout}")
	private Integer pollTimeout;

	@Value("${enable.auto.commit}")
	private String enableAutoCommit;

	@Value("${auto.commit.interval.ms.config}")
	private String autoCommitIntervalMsConfig;

	@Value("${session.timeout.ms.config}")
	private String sessionTimeoutMsConfig;

	@Value("${request.timeout.ms.config}")
	private String requestTimeoutMsConfig;

	@Value("${key.deserializer}")
	private String keyDeserializer;

	@Value("${value.deserializer}")
	private String valueDeserializer;

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(consumerConcurrency);
		factory.setBatchListener(true);
		factory.getContainerProperties().setPollTimeout(pollTimeout);
		factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.BATCH);
		return factory;
	}

	@Bean
	public ConsumerFactory<Integer, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	private Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitIntervalMsConfig);
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeoutMsConfig);
		props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeoutMsConfig);
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumerBatchSize);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
		return props;
	}

}