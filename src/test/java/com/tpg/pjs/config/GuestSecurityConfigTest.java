package com.tpg.pjs.config;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import static org.assertj.core.api.Assertions.assertThat;

public class GuestSecurityConfigTest {

    private GuestSecurityConfig config;

    @Before
    public void setUp() {

        config = new GuestSecurityConfig();
    }

    @Test
    public void configure() {

        BasicAuthenticationEntryPoint actual = (BasicAuthenticationEntryPoint) config.authenticationEntryPoint();

        assertThat(actual.getRealmName()).isEqualTo("pjs:guest");
    }
}
