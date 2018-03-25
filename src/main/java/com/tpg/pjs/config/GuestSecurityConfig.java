package com.tpg.pjs.config;

import com.tpg.pjs.rest.security.PjsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import static com.tpg.pjs.rest.security.WebRole.GUEST;

@Configuration
@ComponentScan(basePackages = {"com.tpg.pjs.rest"})
@Order(1)
public class GuestSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String REALM_NAME = "pjs:guest";

    @Autowired
    private PjsAuthenticationProvider pjsAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {

        authenticationManagerBuilder.authenticationProvider(pjsAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
            .antMatcher("/**").authorizeRequests()
                .anyRequest().hasRole(GUEST.getRole())
                    .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint());
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {

        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();

        entryPoint.setRealmName(REALM_NAME);

        return entryPoint;
    }
}
