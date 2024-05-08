package com.kafkastreams.scheduler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kafkastreams.consumer.KafkaStreamsConsumer;


@Component
public class KafkaStreamScheduler {

	@Autowired
	private KafkaStreamsConsumer consumer;
	
	@Scheduled(fixedDelay = 10000)
	private void productScheduler() {
		System.out.println("Producer Start: "+LocalDateTime.now());
		consumer.streamConsumer();
		System.out.println("Producer End: "+LocalDateTime.now());
	}
}
