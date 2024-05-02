package com.pdf.generator.component;

import com.pdf.generator.model.BillDetails;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class GenerateInvoicePDF {

    public byte[] generateInvoicePDF(BillDetails billDetails) {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

            // working with Font backgroundColor, alignment, margin
            document.add(
                    new Paragraph(billDetails.getCustomerName() + " Invoice")
                            .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                            .setTextAlignment(TextAlignment.CENTER)
                            .setFontSize(28)
                            .setBold()
                            .setFontColor(ColorConstants.RED)
                            .setFont(PdfFontFactory.createFont("Times-Roman"))
            );

            // working with Margin and Padding
            document.add(
                    new Paragraph("Invoice Number: " + billDetails.getInvoiceNumber())
                            .setBold()
                            .setBackgroundColor(ColorConstants.MAGENTA) // add to check margin and padding
                            .setMarginBottom(10) // we can use setMargins(top, right, bottom, left) for all sides
                            .setPaddings(50, 30, 50, 30)
            );
            document.add(new Paragraph("Customer Name: " + billDetails.getCustomerName()));
            document.add(new Paragraph("Invoice Date: " + billDetails.getDate()));

            // working with different text object
            Text amountKey = new Text("Total Amount: ");
            amountKey.setBold();
            amountKey.setFontColor(ColorConstants.BLUE);

            Text amountValue = new Text("" + billDetails.getAmount());
            amountValue.setItalic();

            Paragraph textPara = new Paragraph();
            textPara.add(amountKey);
            textPara.add(amountValue);

            document.add(textPara);

            Table invoiceTable = new Table(4).useAllAvailableWidth();
            invoiceTable.setPaddings(4,2,4,2);
            invoiceTable.setMarginTop(30);
            Cell cell = new Cell();

            invoiceTable.addHeaderCell(
                    new Cell(0,4)
                            .add(new Paragraph("Bill Details")
                                    .setTextAlignment(TextAlignment.CENTER)
                                    .setFontColor(ColorConstants.MAGENTA)
                                    .setBold()
                            )
            );
            // Table Header Row
            invoiceTable.addHeaderCell(
                    new Cell()
                            .add(new Paragraph("Invoice Number")
                                    .setBold()
                                    .setTextAlignment(TextAlignment.CENTER))
            );
            invoiceTable.addHeaderCell(
                    new Cell()
                            .add(new Paragraph("Customer Name")
                                    .setBold()
                                    .setTextAlignment(TextAlignment.CENTER))
            );
            invoiceTable.addHeaderCell(
                    new Cell()
                            .add(new Paragraph("Invoice Date")
                                    .setBold()
                                    .setTextAlignment(TextAlignment.CENTER))
            );
            invoiceTable.addHeaderCell(
                    new Cell()
                            .add(new Paragraph("Total Amount")
                                    .setBold()
                                    .setTextAlignment(TextAlignment.CENTER))
            );

            // Table's Data Row
            invoiceTable.addCell(billDetails.getInvoiceNumber());
            invoiceTable.addCell(billDetails.getCustomerName());
            invoiceTable.addCell(billDetails.getDate().toString());
            invoiceTable.addCell("" + billDetails.getAmount());

            invoiceTable.addCell(billDetails.getInvoiceNumber());
            invoiceTable.addCell(billDetails.getCustomerName());
            invoiceTable.addCell(billDetails.getDate().toString());
            invoiceTable.addCell("" + billDetails.getAmount());

            document.add(invoiceTable);

            document.close();

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
