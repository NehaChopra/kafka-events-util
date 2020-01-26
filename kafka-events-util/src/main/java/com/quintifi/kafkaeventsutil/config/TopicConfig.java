package com.quintifi.kafkaeventsutil.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author nchopra
 *
 */

@Configuration
public class TopicConfig {

	@Value("${topic.name}")
	private String topicName;

	@Value("${topic.partitions}")
	private int topicPartitions;

	@Value("${topic.replicationFactor}")
	private short topicReplicationFactor;

	@Bean
	public NewTopic contructTopic() {
		return new NewTopic(topicName, topicPartitions, topicReplicationFactor);
	}
}
