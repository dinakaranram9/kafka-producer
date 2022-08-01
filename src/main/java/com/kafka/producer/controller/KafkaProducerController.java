package com.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.domain.Metrics;
import com.kafka.producer.service.KafkaService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/produce")
@Api(value = "APIs for produce messages to kafka")
public class KafkaProducerController {

	@Autowired
	KafkaService kafkaService;

	@PostMapping
	String produceMessage(@RequestBody Metrics metrics) {
		return kafkaService.sendKafkaMessage(metrics);
	}
}
