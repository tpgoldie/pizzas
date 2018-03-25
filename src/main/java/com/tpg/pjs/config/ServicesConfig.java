package com.tpg.pjs.config;

import com.tpg.pjs.services.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Bean
    public OrderingService orderingService() {

        return orderingService;
    }
    
    @Autowired
    private OrderingService orderingService;
}
