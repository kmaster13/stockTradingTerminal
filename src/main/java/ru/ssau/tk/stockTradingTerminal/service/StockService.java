package ru.ssau.tk.stockTradingTerminal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.tk.stockTradingTerminal.model.Stock;
import ru.tinkoff.invest.openapi.OpenApi;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {
    @Autowired
    private final OpenApi api;

    @Transactional
    public Stock getStockByTicker(String ticker) {
        var list = api.getMarketContext()
                .searchMarketInstrumentsByTicker(ticker)
                .join()
                .getInstruments();
        var item = list.get(0);
        double price = api.getMarketContext().getMarketOrderbook(item.getFigi(), 0).join().get().getLastPrice().doubleValue();
        return new Stock(
                item.getTicker(),
                item.getFigi(),
                item.getName(),
                item.getType().getValue(),
                price
        );
    }
}