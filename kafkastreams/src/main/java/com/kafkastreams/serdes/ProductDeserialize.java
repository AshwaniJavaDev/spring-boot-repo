package com.kafkastreams.serdes;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.kafkastreams.model.Product;

public class ProductDeserialize implements Deserializer<Product>, Closeable{
	ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public Product deserialize(String topic, byte[] data) {

			if (data == null) {
				return null;
			}
			try {
				String jsonString = new String(data);
				objectMapper.registerModules(new JavaTimeModule());
				return (Product) objectMapper.readValue(jsonString, Product.class);
			} catch (Exception e) {
				throw new SerializationException("Error while deserializing Product object: " + e.getMessage(), e);
			}

		}
}
