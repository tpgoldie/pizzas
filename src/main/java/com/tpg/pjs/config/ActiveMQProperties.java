package com.tpg.pjs.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.activemq")
public class ActiveMQProperties {

    @NotBlank
    @Length(min = 3)
    private String brokerUrl;

    @NotBlank
    private String concurrency;
}
