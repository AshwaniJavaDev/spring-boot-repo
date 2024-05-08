package com.kafkastreams.serdes;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.kafkastreams.model.Product;

public class ProductSerializer implements Serializer<Product>, Closeable{

	@Override
	public byte[] serialize(String topic, Product data) {
		
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
			outputStream.writeObject(data);
			outputStream.close();
			return byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			throw new SerializationException("Error while serializing Product object: "+e.getMessage());
		}
		
	}

}
