package com.quintifi.kafkaeventsutil.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
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

	@Value("${kafka.broker.retry.config}")
	private String retryConfig;

	@Value("${kafka.broker.batch.size.config}")
	private String batchSizeConfig;

	@Value("${kafka.broker.linger.ms.config}")
	private String lingerMsConfig;

	@Value("${kafka.broker.compression.type.config}")
	private String compressionTypeConfig;

	@Value("${kafka.broker.buffer.memory.config}")
	private String buggerMemoryConfig;

	@Value("${kafka.broker.max.in.flight}")
	private String masInFlight;

	@Value("${kafka.broker.request.timeout.ms.config}")
	private String requestTimeoutMsConfig;

	@Value("${key.serializer}")
	private String keySerializer;

	@Value("${value.serializer}")
	private String valueSerializer;

	@Qualifier("kafkaTemplate")
	@Bean(name = "kafkaTemplate")
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Qualifier("producerFactory")
	@Bean(name = "producerFactory")
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(setProducerConfig());
	}

	public Map<String, Object> setProducerConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
		props.put(ProducerConfig.RETRIES_CONFIG, retryConfig);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSizeConfig);// Batch up to 64K buffer sizes.
		props.put(ProducerConfig.LINGER_MS_CONFIG, lingerMsConfig);// Linger up to 100 ms before sending batch if size
																	// not met
		props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionTypeConfig);// Use Snappy compression for batch
																					// compression.
		// high speeds and reasonable compressio
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, buggerMemoryConfig);
		props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, masInFlight);
		props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeoutMsConfig);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
		return props;
	}

}
