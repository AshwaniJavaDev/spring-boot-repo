package com.kafkastreams.consumer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

import com.kafkastreams.model.ProductSerializer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kafkastreams.model.Product;
import org.springframework.util.SerializationUtils;

@Component
public class KafkaStreamsConsumer {
	
	@Autowired(required = true)
	private KafkaProducer<String,byte[]> kafkaProducer;

	ObjectMapper mapper = new ObjectMapper();
	ProductSerializer productSerializer = new ProductSerializer();

	public void streamConsumer() {
		
		try {
			mapper.registerModule(new JavaTimeModule());
			Product product = mapper.readValue(new File("C://Ashwani//dummy//product.json"), Product.class);
			product.setPid(new Random().nextInt(1000));
			product.setCreatedAt(LocalDateTime.now());
			product.setTitle(product.getTitle()+" "+new Random().nextInt(1, 15));
			product.setPrice(product.getPrice()+new Random().nextInt(1, 100));
			//byte productData[] = productSerializer.serialize("kafka.stream.input",product);
			byte productData[] = mapper.writeValueAsString(product).getBytes();
			ProducerRecord<String, byte[]> producerRecord = new ProducerRecord<>("kafka.stream.input", productData);
			kafkaProducer.send(producerRecord);
			System.out.println(product.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
