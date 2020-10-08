package com.github.payments.config;

import com.github.currencies.rates.controllers.IExchangeController;
import com.github.currencies.rates.controllers.impl.ExchangeController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableCaching
@EntityScan(value = "com.github.payments.entity")
@EnableJpaRepositories(basePackages = "com.github.payments.repository")
public class AppConfig {

    @Value(value = "${exchange.url}")
    private String exchangeUrl;

    @Bean
    public IExchangeController exchangeController() {
        return new ExchangeController(this.exchangeUrl);
    }

}
