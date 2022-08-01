package com.kafka.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {
	
	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${spring.kafka.producer.key-serializer}")
	private String keySerializer;
	
	@Value("${spring.kafka.producer.value-serializer}")
	private String valueSerializer;
	
	@Value("${spring.kafka.producer.acks}")
	private String acks;
	
	@Value("${spring.kafka.producer.retries}")
	private int retries;
	
	@Value("${spring.kafka.producer.batch-size}")
	private int batchSize;
	
	@Value("${producer.linger.ms}")
	private int linger;
	
	/**
	 * Builds the producer properties
	 * 
	 * @return Map<String, Object>
	 */
	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
		props.put(ProducerConfig.ACKS_CONFIG, acks);
		props.put(ProducerConfig.RETRIES_CONFIG, retries);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
		props.put(ProducerConfig.LINGER_MS_CONFIG, linger);

		return props;
	}
	
	@Bean
	public ProducerFactory<Integer, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<Integer, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}


}
