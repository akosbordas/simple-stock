package com.github.akosbordas.simple.stock.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class Trade {

    private DateTime time;
    private Stock stock;
    private TradeIndicator tradeIndicator = TradeIndicator.BUY;
    private int sharesQuantity;
    private double price;

    public Trade(DateTime time, Stock stock, TradeIndicator tradeIndicator, int sharesQuantity, double price) {
        this.time = time;
        this.stock = stock;
        this.tradeIndicator = tradeIndicator;
        this.sharesQuantity = sharesQuantity;
        this.price = price;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public TradeIndicator getTradeIndicator() {
        return tradeIndicator;
    }

    public void setTradeIndicator(TradeIndicator tradeIndicator) {
        this.tradeIndicator = tradeIndicator;
    }

    public int getSharesQuantity() {
        return sharesQuantity;
    }

    public void setSharesQuantity(int sharesQuantity) {
        this.sharesQuantity = sharesQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trade{");
        sb.append("time=").append(time != null ? DateTimeFormat.forPattern("HH:mm:ss").print(time) : "");
        sb.append(", stockSymbol=").append(stock != null ? stock.getStockSymbol() : "");
        sb.append(", tradeIndicator=").append(tradeIndicator);
        sb.append(", sharesQuantity=").append(sharesQuantity);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
