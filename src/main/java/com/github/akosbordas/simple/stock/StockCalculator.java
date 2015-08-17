package com.github.akosbordas.simple.stock;

import com.github.akosbordas.simple.stock.model.Stock;
import com.github.akosbordas.simple.stock.model.StockType;
import com.github.akosbordas.simple.stock.model.Trade;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.joda.time.DateTime;

import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

public class StockCalculator {

    public static final StockCalculator INSTANCE = new StockCalculator();

    private StockCalculator() {
    }

    public double calculateDividendYield(Stock stock) {
        checkNotNull(stock);
        if (stock.getPrice() == 0) {
            return 0;
        }

        if (stock.getStockType() == StockType.COMMON) {
            return stock.getLastDividend() / stock.getPrice();
        } else {
            return (stock.getFixedDividend() * stock.getPar()) / stock.getPrice();
        }
    }

    public double calculatePeRatio(Stock stock) {
        checkNotNull(stock);
        double earnings = calculateDividendYield(stock);
        return earnings > 0 ? stock.getPrice() / earnings : 0;
    }

    public double calculateStockPrice(List<Trade> trades, final Stock stock) {
        checkNotNull(stock);
        final DateTime fifteenMinutesEarlier = DateTime.now().minusMinutes(15);
        Collection<Trade> las15minutes = Collections2.filter(trades, new Predicate<Trade>() {
            @Override
            public boolean apply(Trade input) {
                return input.getStock() != null &&
                       input.getStock().getStockSymbol() != null &&
                       input.getStock().getStockSymbol().equals(stock.getStockSymbol()) &&
                       input.getTime().isAfter(fifteenMinutesEarlier);
            }
        });

        return calculateStockPriceOnTrades(las15minutes, stock);
    }

    public double calculateGBCEAllShareIndex(List<Trade> trades, List<Stock> stocks)  {
        List<Double> stockPrices = newArrayList();
        for (Stock stock : stocks) {
            double stockPrice = calculateStockPriceOnTrades(trades, stock);
            if (stockPrice > 0) {
                stockPrices.add(stockPrice);
            }
        }
        return StatUtils.geometricMean(ArrayUtils.toPrimitive(stockPrices.toArray(new Double[0])));
    }

    private double calculateStockPriceOnTrades(Collection<Trade> trades, Stock stock) {
        checkNotNull(stock);
        double quantity = 0.0;
        double pricePerQuantity = 0.0;
        for (Trade trade : trades) {
            pricePerQuantity += (trade.getPrice() * trade.getSharesQuantity());
            quantity += trade.getSharesQuantity();
        }

        return quantity > 0 ? pricePerQuantity / quantity : 0;
    }

}
