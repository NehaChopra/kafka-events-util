package com.quintifi.kafkaeventsutil.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * 
 * @author nchopra
 *
 */

@Configuration
public class KafkaProducerConfig {

	@Value("${kafka.broker.address}")
	private String brokerAddress;

	@Qualifier("kafkaTemplate")
	@Bean(name = "kafkaTemplate")
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Qualifier("producerFactory")
	@Bean(name = "producerFactory")
	public ProducerFactory<String, String> producerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
		setProducerConfig(props);
		return new DefaultKafkaProducerFactory<>(props);
	}

	public Map<String, Object> setProducerConfig(Map<String, Object> props) {
		props.put(ProducerConfig.RETRIES_CONFIG, 5);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384 * 4);// Batch up to 64K buffer sizes.
		props.put(ProducerConfig.LINGER_MS_CONFIG, 100);// Linger up to 100 ms before sending batch if size not met
		props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");// Use Snappy compression for batch compression.
																	// high speeds and reasonable compressio
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 10);
		props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 15000);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return props;
	}

}
