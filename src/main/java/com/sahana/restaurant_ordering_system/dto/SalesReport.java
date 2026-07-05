package com.sahana.restaurant_ordering_system.dto;

public class SalesReport {

    private int totalOrders;
    private Double totalRevenue;

    public SalesReport() {
    }

    public SalesReport(int totalOrders, Double totalRevenue) {
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
