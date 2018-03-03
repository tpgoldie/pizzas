package com.tpg.pjs.rest.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.tpg.pjs.rest.security.WebRole.GUEST;
import static com.tpg.pjs.rest.security.WebRole.PJS_USER;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PjsAuthenticationProviderTest {

    @Mock
    private Authentication authentication;

    private PjsAuthenticationProvider provider;

    @Before
    public void setUp() {

        provider = new PjsAuthenticationProvider();
    }

    @Test
    public void authenticateGuest() {

        when(authentication.getName()).thenReturn(PjsWebUser.PJS_WEB_USER.getUsername());
        when(authentication.getCredentials()).thenReturn(PjsWebUser.PJS_WEB_USER.getPassword());

        UsernamePasswordAuthenticationToken actual = (UsernamePasswordAuthenticationToken) provider.authenticate(authentication);

        assertThat(actual.getName()).isEqualTo("pjs");
        assertThat(actual.getCredentials().toString()).isEqualTo("pjs");
        assertThat(actual.getAuthorities()).isEqualTo(singletonList(new SimpleGrantedAuthority(GUEST.getRole())));
    }

    @Test
    public void authenticateExistingUser() {

        when(authentication.getName()).thenReturn("jdoe");
        when(authentication.getCredentials()).thenReturn("abc-123");

        UsernamePasswordAuthenticationToken actual = (UsernamePasswordAuthenticationToken) provider.authenticate(authentication);

        assertThat(actual.getName()).isEqualTo("jdoe");
        assertThat(actual.getCredentials().toString()).isEqualTo("abc-123");
        assertThat(actual.getAuthorities()).isEqualTo(singletonList(new SimpleGrantedAuthority(PJS_USER.getRole())));
    }

    @Test
    public void handleMissingUsername() {

        when(authentication.getCredentials()).thenReturn("abc-123");

        Authentication actual = provider.authenticate(authentication);

        assertThat(actual).isNull();
    }

    @Test
    public void handleMissingPassword() {

        when(authentication.getName()).thenReturn("jdoe");

        Authentication actual = provider.authenticate(authentication);

        assertThat(actual).isNull();
    }
}
