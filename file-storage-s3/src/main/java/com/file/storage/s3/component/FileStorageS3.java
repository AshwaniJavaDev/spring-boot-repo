package com.file.storage.s3.component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
@Slf4j
public class FileStorageS3 {

    Logger logger = LoggerFactory.getLogger(FileStorageS3.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.s3.bucket}")
    private String s3BucketName;

    public void uploadFileToS3(byte[] pdfFileData) {
        logger.info("Inside the uploadFileToS3 method!!!");
        String fileName = "invoice_" + System.currentTimeMillis() + ".pdf";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfFileData);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(pdfFileData.length);
        s3Client.putObject(new PutObjectRequest(s3BucketName, fileName, inputStream, metadata));
        logger.info("File Uploaded to S3!!!");
    }

    public byte[] dowanloadFileFromS3() {

        try {
            S3Object s3Object = s3Client.getObject(s3BucketName, "invoice_1714735876954.pdf");
            S3ObjectInputStream objectContent = s3Object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFileFromS3(){
        s3Client.deleteObject(s3BucketName, "invoice_1714735876954.pdf");
    }
}
