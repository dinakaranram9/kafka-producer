package com.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.producer.domain.Metrics;

@Service
public class KafkaService {

	@Value("${kafka.producer.topic}")
	private String kafkaProducerTopic;

	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	public String sendKafkaMessage(Metrics metrics) {
		//sendMessage(metrics);
		kafkaTemplate.send(kafkaProducerTopic,
			metrics.toString());
		return "message placesd in the queue successfully";
	}

/*	public void sendMessage(Metrics metrics) {

		ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(kafkaProducerTopic,
				metrics.toString());

		future.addCallback( new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("Sent message=[" + metrics.toString() + "] with offset=["
						+ result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + metrics.toString() + "] due to : " + ex.getMessage());
			}
		},null ); 
	} */

}
