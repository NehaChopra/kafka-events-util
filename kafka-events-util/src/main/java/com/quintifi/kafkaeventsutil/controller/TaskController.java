package com.quintifi.kafkaeventsutil.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quintifi.kafkaeventsutil.domain.network.request.BaseRequest;
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

	@PostMapping("/sync/payload")
	public void pushPayload(BaseRequest request) {
		LOGGER.debug("Inside function findAll of TodoTaskController...........Retrieving tasks !!");

		if (StringUtils.isNotEmpty(request.getTopic()) && !ObjectUtils.isEmpty(request.getPartition())
				&& StringUtils.isNotEmpty(request.getKey()) && !ObjectUtils.isEmpty(request.getTimestamp())
				&& StringUtils.isNotEmpty(request.getMessage())) {
			try {
				kafkaProducerService.sendMessage(request.getTopic(), request.getPartition(), request.getKey(),
						request.getTimestamp(), request.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (StringUtils.isNotEmpty(request.getTopic()) && !ObjectUtils.isEmpty(request.getPartition())
				&& StringUtils.isNotEmpty(request.getKey()) && StringUtils.isNotEmpty(request.getMessage())) {
			try {
				kafkaProducerService.sendMessage(request.getTopic(), request.getPartition(), request.getKey(),
						request.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (StringUtils.isNotEmpty(request.getTopic()) && StringUtils.isNotEmpty(request.getKey())
				&& StringUtils.isNotEmpty(request.getMessage())) {
			try {
				kafkaProducerService.sendMessage(request.getTopic(), request.getKey(), request.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (StringUtils.isNotEmpty(request.getTopic()) && StringUtils.isNotEmpty(request.getMessage())) {
			try {
				kafkaProducerService.sendMessage(request.getTopic(), request.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@PostMapping("/async/payload")
	public void pushAsyncPayload(BaseRequest request) {
		LOGGER.debug("Inside function findAll of TodoTaskController...........Retrieving tasks !!");

		if (StringUtils.isNotEmpty(request.getTopic()) && !ObjectUtils.isEmpty(request.getPartition())
				&& StringUtils.isNotEmpty(request.getKey()) && !ObjectUtils.isEmpty(request.getTimestamp())
				&& StringUtils.isNotEmpty(request.getMessage())) {
			try {
				kafkaAsyncProducerService.sendMessage(request.getTopic(), request.getPartition(), request.getKey(),
						request.getTimestamp(), request.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (StringUtils.isNotEmpty(request.getTopic()) && !ObjectUtils.isEmpty(request.getPartition())
				&& StringUtils.isNotEmpty(request.getKey()) && StringUtils.isNotEmpty(request.getMessage())) {
			try {
				kafkaAsyncProducerService.sendMessage(request.getTopic(), request.getPartition(), request.getKey(),
						request.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (StringUtils.isNotEmpty(request.getTopic()) && StringUtils.isNotEmpty(request.getKey())
				&& StringUtils.isNotEmpty(request.getMessage())) {
			try {
				kafkaAsyncProducerService.sendMessage(request.getTopic(), request.getKey(), request.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (StringUtils.isNotEmpty(request.getTopic()) && StringUtils.isNotEmpty(request.getMessage())) {
			try {
				kafkaAsyncProducerService.sendMessage(request.getTopic(), request.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
