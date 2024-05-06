package com.email.sender.model;

public class Charges {
    private double totalUsage;
    private int totalHours;
    private int usageRate;

    public Charges(double totalUsage, int totalHours, int usageRate) {
        this.totalUsage = totalUsage;
        this.totalHours = totalHours;
        this.usageRate = usageRate;
    }

    public double getTotalUsage() {
        return totalUsage;
    }

    public void setTotalUsage(double totalUsage) {
        this.totalUsage = totalUsage;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public int getUsageRate() {
        return usageRate;
    }

    public void setUsageRate(int usageRate) {
        this.usageRate = usageRate;
    }
}
