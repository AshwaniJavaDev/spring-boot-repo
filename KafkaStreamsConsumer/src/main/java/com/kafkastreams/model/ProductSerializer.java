package com.kafkastreams.model;

import org.apache.kafka.common.serialization.Serializer;

import java.io.*;

public class ProductSerializer implements Serializer<Product> {

    @Override
    public byte[] serialize(String topic, Product data) {
        if (data == null) {
            return null;
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(baos)) {
            outputStream.writeObject(data);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error serializing Product object: ", e);
        }
    }
}