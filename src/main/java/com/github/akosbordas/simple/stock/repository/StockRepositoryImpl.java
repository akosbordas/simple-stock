package com.github.akosbordas.simple.stock.repository;

import com.github.akosbordas.simple.stock.model.Stock;
import com.github.akosbordas.simple.stock.model.Trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class StockRepositoryImpl implements StockRepository {

    public static final StockRepositoryImpl INSTANCE = new StockRepositoryImpl();

    private StockRepositoryImpl() {
    }

    private HashMap<String, Stock> stocks = newHashMap();
    private List<Trade> trades = newArrayList();

    public HashMap<String, Stock> findStocks() {
        return stocks;
    }

    public void saveStock(String symbol, Stock stock) {
        stocks.put(symbol, stock);
    }

    public void saveStocks(List<Stock> stocks) {
        for (Stock stock : stocks) {
            saveStock(stock.getStockSymbol(), stock);
        }
    }

    public void setStocks(HashMap<String, Stock> stocks) {
        this.stocks = stocks;
    }

    public List<Trade> findTrades() {
        return trades;
    }

    public void setTrades(ArrayList<Trade> trades) {
        this.trades = trades;
    }

    public Trade saveTrade(Trade trade) {
        trades.add(trade);
        return trade;
    }

    public Stock findStockBySymbol(String symbol) {
        return stocks.get(symbol);
    }

}
