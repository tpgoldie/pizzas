package com.tpg.pjs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@EnableJms
@Configuration
public class ActiveMQConfig {

    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory() {

        return new DefaultJmsListenerContainerFactory();
    }

    public static final String ORDER_QUEUE = "orders-queue";

    public static final String ORDER_STATUS_QUEUE = "orders-status-queue";
}
