package com.kafkastreams.serdes;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.kafkastreams.model.Product;

public class ProductSerdes implements Serde<Product> {

	private final ProductSerializer productSerializer;
	private final ProductDeserialize productDeserialize;
	
	
	
	public ProductSerdes() {
		this.productSerializer = new ProductSerializer();
		this.productDeserialize = new ProductDeserialize();
	}

	@Override
	public Serializer<Product> serializer() {
		return productSerializer;
	}

	@Override
	public Deserializer<Product> deserializer() {
		return productDeserialize;
	}

}
