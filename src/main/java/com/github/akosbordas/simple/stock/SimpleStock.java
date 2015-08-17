package com.github.akosbordas.simple.stock;

import com.github.akosbordas.simple.stock.model.Stock;
import com.github.akosbordas.simple.stock.model.StockType;
import com.github.akosbordas.simple.stock.model.Trade;
import com.github.akosbordas.simple.stock.model.TradeIndicator;
import com.github.akosbordas.simple.stock.repository.StockRepository;
import com.github.akosbordas.simple.stock.repository.StockRepositoryImpl;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;

public class SimpleStock {

    private static final Logger logger = Logger.getLogger(SimpleStock.class);

    public static void main(String[] args) {

        StockRepository repository = StockRepositoryImpl.INSTANCE;
        StockCalculator calculator = StockCalculator.INSTANCE;


        List<Stock> stocks = newArrayList(new Stock("TEA", StockType.COMMON, 0, 0, 100),
                                          new Stock("POP", StockType.COMMON, 8, 0, 100),
                                          new Stock("ALE", StockType.COMMON, 23, 0, 60),
                                          new Stock("GIN", StockType.PREFERRED, 8, 2, 100),
                                          new Stock("JOE", StockType.COMMON, 13, 0, 250));

        repository.saveStocks(stocks);
        logger.debug("Saved stocks: " + stocks);
        logger.debug("-----------------------------------------------------------------------------------------------");

        Random random = new Random();
        int numberOfTrades = args.length > 0 ? Integer.parseInt(args[0]) : getRandomIntBetween(100, 50);
        logger.debug("Generating [" + numberOfTrades+ "] trades");
        for (int i = 0; i < numberOfTrades; i++) {
            Stock stock = stocks.get(getRandomIntBetween(stocks.size(), 0));
            DateTime randomDate =
                random.nextBoolean() ? DateTime.now().minusMinutes(getRandomIntBetween(10, 5)) : DateTime.now()
                                                                                                         .minusMinutes(getRandomIntBetween(30, 18));
            TradeIndicator indicator = random.nextBoolean() ? TradeIndicator.BUY : TradeIndicator.SELL;
            int sharesQuantity = getRandomIntBetween(500, 100);
            double price = getRandomDoubleBetween(500, 100);
            Trade trade = new Trade(randomDate, stock, indicator, sharesQuantity, price);
            logger.debug(trade);

            repository.saveTrade(trade);
            stock.setPrice(trade.getPrice());
        }
        logger.debug("-----------------------------------------------------------------------------------------------");
        for (Stock stock : stocks) {
            double dividendYield = calculator.calculateDividendYield(stock);
            double peRatio = calculator.calculatePeRatio(stock);
            double stockPrice = calculator.calculateStockPrice(repository.findTrades(), stock);
            logger.debug("Symbol: "+ stock.getStockSymbol() + " Dividend Yield: " + dividendYield + " P/E ratio: "+ peRatio + " Stock Price: "+ stockPrice);
        }
        logger.debug("-----------------------------------------------------------------------------------------------");
        double gbceAllShareIndex = calculator.calculateGBCEAllShareIndex(repository.findTrades(), stocks);
        logger.debug("GBCE All Share Index: "+gbceAllShareIndex);

    }

    private static int getRandomIntBetween(int high, int low) {
        Random random = new Random();
        return random.nextInt(high - low) + low;
    }

    private static double getRandomDoubleBetween(double high, double low) {
        Random random = new Random();
        return random.nextDouble()*high + low;
    }

}
