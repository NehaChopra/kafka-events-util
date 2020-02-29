package com.quintifi.kafkaeventsutil.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.quintifi.kafkaeventsutil.service.KafkaAsyncProducerService;
import com.quintifi.kafkaeventsutil.service.KafkaProducerService;

/**
 * 
 * @author nchopra
 *
 */
@RestController
public class TaskController {

	private final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@Autowired
	private KafkaAsyncProducerService kafkaAsyncProducerService;

	// @GetMapping("/api/todoTasks")
	// public List<TodoTask> findAll() {
	// LOGGER.info("Inside function findAll of TodoTaskController............
	// Retrieving tasks !!");
	// return todoTaskService.findAll();
	// }
}
