package com.kafkastreams.processor;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.kafkastreams.model.Product;
import com.kafkastreams.serdes.ProductDeserialize;
import com.kafkastreams.serdes.ProductSerdes;
import com.kafkastreams.serdes.ProductSerializer;

import jakarta.annotation.PostConstruct;

@Component
public class EventInputStreamProcessor {

	@Autowired
	private StreamsBuilder streamsBuilder;

	@Bean
	public void eventStreamInputTopology() {
		KStream<String, Product> inputStream = streamsBuilder.stream("kafka.stream.input",
				Consumed.with(Serdes.String(), new ProductSerdes()));

		KStream<String, Product> outputStream = inputStream
				.filter((key, value) -> value.getTitle().startsWith("iPhone 1") && value.getPrice() >= 550);
		outputStream.to("kafka.stream.output", Produced.with(Serdes.String(), new ProductSerdes()));
	}
	
	@Bean
	public Serde<Product> productSerdes(){
		return Serdes.serdeFrom(new ProductSerializer(), new ProductDeserialize());
	}
}
