package com.github.akosbordas.simple.stock.model;

public class Stock {

    private String stockSymbol;
    private StockType stockType = StockType.COMMON;
    private double lastDividend;
    private double fixedDividend;
    private double par;
    private double price;

    public Stock(String stockSymbol, StockType stockType, double lastDividend, double fixedDividend, double par) {
        this.stockSymbol = stockSymbol;
        this.stockType = stockType;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.par = par;
        this.price = price;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public StockType getStockType() {
        return stockType;
    }

    public void setStockType(StockType stockType) {
        this.stockType = stockType;
    }

    public double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public double getPar() {
        return par;
    }

    public void setPar(double par) {
        this.par = par;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Stock{");
        sb.append("stockSymbol='").append(stockSymbol).append('\'');
        sb.append(", stockType=").append(stockType);
        sb.append(", lastDividend=").append(lastDividend);
        sb.append(", fixedDividend=").append(fixedDividend);
        sb.append(", par=").append(par);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
