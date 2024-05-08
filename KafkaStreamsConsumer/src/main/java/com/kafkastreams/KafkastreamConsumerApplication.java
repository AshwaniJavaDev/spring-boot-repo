package com.kafkastreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
public class KafkastreamConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkastreamConsumerApplication.class, args);
	}

}
