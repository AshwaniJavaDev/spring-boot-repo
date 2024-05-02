package com.pdf.generator.model;

import java.time.LocalDate;

public class BillDetails {

    private int billID;
    private String invoiceNumber;
    private String customerName;
    private LocalDate date;
    private double amount;

    public BillDetails(int billID, String invoiceNumber, String customerName, LocalDate date, double amount) {
        this.billID = billID;
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.date = date;
        this.amount = amount;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
