package ru.ssau.tk.stockTradingTerminal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.tk.stockTradingTerminal.model.Stock;
import ru.ssau.tk.stockTradingTerminal.repository.StocksRepository;
import ru.tinkoff.invest.openapi.OpenApi;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StockService {
    @Autowired
    private final OpenApi api;

    @Autowired
    private final StocksRepository stocksRepository;

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
                item.getName(),
                price
        );
    }

    @Transactional
    public Set<Stock> getDefaultStocks() {
        Set<Stock> set = new HashSet<>();
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
        for (String ticker : tickers) {
            set.add(getStockByTicker(ticker));
            stocksRepository.save(getStockByTicker(ticker));
        }
        return set;
    }
}