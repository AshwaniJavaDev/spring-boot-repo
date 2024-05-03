package com.file.storage.s3.controller;

import com.file.storage.s3.component.FileStorageS3;
import com.file.storage.s3.component.GeneratePDFForS3;
import com.file.storage.s3.model.InvoiceDetails;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@Slf4j
public class S3Controller {

    Logger logger = LoggerFactory.getLogger(S3Controller.class);

    @Autowired
    private GeneratePDFForS3 pdfForS3;

    @Autowired
    private FileStorageS3 storageS3;

    @GetMapping("/upload-pdf")
    public ResponseEntity<String> uploadFileToS3() {

        logger.info("Calling /upload-pdf API !!!");
        InvoiceDetails invoiceDetails = new InvoiceDetails("In-2024-as", "Ashwani", LocalDate.now(), 2000);

        logger.info("Calling  generatePDFForS3 method!!!");
        byte[] data = pdfForS3.generatePDFForS3(invoiceDetails);

        logger.info("Calling  uploadFileToS3 method!!!");
        storageS3.uploadFileToS3(data);

        return new ResponseEntity<>("File Uploaded Successfully", HttpStatus.OK);

    }

    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> downloadFileFromS3(){
        byte[] fileData = storageS3.dowanloadFileFromS3();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        httpHeaders.setContentDispositionFormData("filename", "invoice_1714735876954.pdf");
        return new ResponseEntity<>(fileData, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/delete-pdf")
    public ResponseEntity<String> deleteFileFromS3(){
        storageS3.deleteFileFromS3();
        return new ResponseEntity<>("File deleted successfully", HttpStatus.OK);
    }

}
