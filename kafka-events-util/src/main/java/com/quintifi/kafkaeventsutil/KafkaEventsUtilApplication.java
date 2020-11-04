package com.quintifi.kafkaeventsutil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaEventsUtilApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaEventsUtilApplication.class, args);
	}

}