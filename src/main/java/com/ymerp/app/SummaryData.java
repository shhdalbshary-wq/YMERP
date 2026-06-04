package com.ymerp.app;

/**
 * فئة بيانات الملخص
 * SummaryData - تمثل بيانات الملخص المالي
 */
public class SummaryData {

    private double invoices;
    private int clients;
    private double sales;
    private double expenses;

    public SummaryData(double invoices, int clients, double sales, double expenses) {
        this.invoices = invoices;
        this.clients = clients;
        this.sales = sales;
        this.expenses = expenses;
    }

    // Getters
    public double getInvoices() {
        return invoices;
    }

    public int getClients() {
        return clients;
    }

    public double getSales() {
        return sales;
    }

    public double getExpenses() {
        return expenses;
    }

    // Setters
    public void setInvoices(double invoices) {
        this.invoices = invoices;
    }

    public void setClients(int clients) {
        this.clients = clients;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    // حساب صافي الربح
    public double getNetProfit() {
        return sales - expenses;
    }

    // حساب نسبة الربح
    public double getProfitMargin() {
        if (sales == 0) return 0;
        return (getNetProfit() / sales) * 100;
    }

    @Override
    public String toString() {
        return "SummaryData{" +
                "invoices=" + invoices +
                ", clients=" + clients +
                ", sales=" + sales +
                ", expenses=" + expenses +
                ", netProfit=" + getNetProfit() +
                '}';
    }
}
