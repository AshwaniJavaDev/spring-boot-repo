package com.pdf.generator.controller;

import com.pdf.generator.component.GenerateInvoicePDF;
import com.pdf.generator.model.BillDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class GenerateInvoicePDFController {

    @Autowired
    private GenerateInvoicePDF invoicePDF;

    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generateInvoicePDF() {

        BillDetails billDetails = new BillDetails(
                1,
                "IN-123-2024",
                "Ashwani",
                LocalDate.of(2024, 05, 02),
                15000);
        byte[] pdfByte = invoicePDF.generateInvoicePDF(billDetails);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        httpHeaders.setContentDispositionFormData("filename","invoice.pdf");
        return new ResponseEntity<>(pdfByte, httpHeaders, HttpStatus.OK);
    }
}
