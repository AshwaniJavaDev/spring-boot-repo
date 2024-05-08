package com.kafkastreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class KafkastreamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkastreamsApplication.class, args);
	}

}
