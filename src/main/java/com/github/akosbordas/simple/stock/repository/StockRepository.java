package com.github.akosbordas.simple.stock.repository;

import com.github.akosbordas.simple.stock.model.Stock;
import com.github.akosbordas.simple.stock.model.Trade;

import java.util.HashMap;
import java.util.List;

public interface StockRepository {

	public Trade saveTrade(Trade trade);
	public List<Trade> findTrades();
	public Stock findStockBySymbol(String symbol);
	public HashMap<String, Stock> findStocks();
	public void saveStock(String symbol, Stock stock);
	public void saveStocks(List<Stock> stocks);
}
