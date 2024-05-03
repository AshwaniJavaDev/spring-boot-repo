package com.file.storage.s3.component;

import com.file.storage.s3.model.InvoiceDetails;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
@Slf4j
public class GeneratePDFForS3 {

    Logger logger = LoggerFactory.getLogger(GeneratePDFForS3.class);

    public byte[] generatePDFForS3(InvoiceDetails invoiceDetails){
        logger.info("Inside the generatePDFForS3 method!!!");
        try(
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PdfWriter pdfWriter = new PdfWriter(outputStream);
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                Document document = new Document(pdfDocument);
        ){

            document.add(
                    new Paragraph(invoiceDetails.getCustomerName()+" Invoice")
                            .setFont(PdfFontFactory.createFont("Times-Roman"))
                            .setBold()
                            .setFontSize(28)
                            .setFontColor(ColorConstants.RED));
            document.add(new Paragraph("Invoice Number: "+ invoiceDetails.getInvoiceNumber()));
            document.add(new Paragraph("Customer Name: "+ invoiceDetails.getCustomerName()));
            document.add(new Paragraph("Invoice Date: "+ invoiceDetails.getInvoiceDate()));
            document.add(new Paragraph("Invoice Amount: "+ invoiceDetails.getInvoiceAmount()));

            document.close();
            logger.info("Document created and returning byte stream!!!");
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
