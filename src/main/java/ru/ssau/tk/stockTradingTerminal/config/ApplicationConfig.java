package ru.ssau.tk.stockTradingTerminal.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    @Bean
    public OpenApi api() {
        return new OkHttpOpenApi("t.08UlbhG45Y6uexcWJtQMeZfyvsY5HrVSvzxvRIoQqG1iHwCkq4yJSGZkGx1R4leF_eNtG2-_PRpjQtHRfw0ZqQ", true);
    }
}
