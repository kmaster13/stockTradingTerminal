package ru.ssau.tk.stockTradingTerminal.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssau.tk.stockTradingTerminal.model.Stock;
import ru.ssau.tk.stockTradingTerminal.repository.StocksRepository;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {
    @Autowired
    private StockService stockService;
    @MockBean
    private OpenApi api;
    @MockBean
    private StocksRepository stocksRepository;

    @Test(expected = NullPointerException.class)
    public void getStockByTicker() {
        String ticker = "SBER";
        MarketInstrument item = api.getMarketContext()
                .searchMarketInstrumentsByTicker(ticker)
                .join()
                .getInstruments().get(0);
        double price = api.getMarketContext().getMarketOrderbook(item.getFigi(), 0).join().get().getLastPrice().doubleValue();
        Assert.assertEquals(stockService.getStockByTicker(ticker), new Stock(item.getTicker(), item.getName(), price));
    }

    @Test(expected = NullPointerException.class)
    public void updateStocks() {
        stockService.updateStocks();
        Mockito.verify(stocksRepository, Mockito.times(stockService.getDefaultStocks().size())).save(ArgumentMatchers.any());
    }

    @Test(expected = NullPointerException.class)
    public void getDefaultStocks() {
        Assert.assertEquals(stockService.getDefaultStocks(), stocksRepository.findAll());
        Mockito.verify(stocksRepository, Mockito.times(stockService.getDefaultStocks().size())).save(ArgumentMatchers.any());
        Mockito.verify(stocksRepository, Mockito.times(1)).findAll();
    }
}