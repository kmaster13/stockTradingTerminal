package ru.ssau.tk.stockTradingTerminal.service;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.tk.stockTradingTerminal.model.Stock;
import ru.ssau.tk.stockTradingTerminal.repository.StocksRepository;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@NoArgsConstructor
public class StockService {
    private OpenApi api;
    private StocksRepository stocksRepository;
    @Autowired
    public StockService(OpenApi api, StocksRepository stocksRepository) {
        this.api = api;
        this.stocksRepository = stocksRepository;
    }

    @Transactional
    public Stock getStockByTicker(String ticker) {
        List<MarketInstrument> list = api.getMarketContext()
                .searchMarketInstrumentsByTicker(ticker)
                .join()
                .getInstruments();
        MarketInstrument item = list.get(0);
        double price = api.getMarketContext().getMarketOrderbook(item.getFigi(), 0).join().get().getLastPrice().doubleValue();
        return new Stock(
                item.getTicker(),
                item.getName(),
                price
        );
    }

    private List<String> getListStocks() {
        List<String> tickers = new ArrayList<>();
        {
            tickers.add("FIXP");
            tickers.add("OZON");
            tickers.add("QIWI");
            tickers.add("VKCO");
            tickers.add("AFLT");
            tickers.add("VTBR");
            tickers.add("GAZP");
            tickers.add("BANE");
            tickers.add("DSKY");
            tickers.add("KMAZ");
            tickers.add("LENT");
            tickers.add("LKOH");
            tickers.add("MVID");
            tickers.add("MGNT");
            tickers.add("MOEX");
            tickers.add("MTSS");
            tickers.add("ROSN");
            tickers.add("RTKM");
            tickers.add("TATN");
            tickers.add("YNDX");
            tickers.add("SBER");
        }
        return tickers;
    }

    public void updateStocks() {
        List<String> tickers = getListStocks();
        Set<Stock> set = new HashSet<>();
        for (String ticker : tickers) {
            set.add(getStockByTicker(ticker));
            stocksRepository.save(getStockByTicker(ticker));
        }
    }

    @Transactional
    public List<Stock> getDefaultStocks() {
        updateStocks();
        return stocksRepository.findAll();
    }
}