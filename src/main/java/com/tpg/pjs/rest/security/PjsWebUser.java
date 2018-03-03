package com.tpg.pjs.rest.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static com.tpg.pjs.rest.security.WebRole.GUEST;
import static java.util.Collections.singletonList;

@Getter
public final class PjsWebUser {

    public static final PjsWebUser PJS_WEB_USER = new PjsWebUser();

    private final String username = "pjs";
    private final String password = "pjs";
    private final List<GrantedAuthority> authorities = singletonList(new SimpleGrantedAuthority(GUEST.getRole()));

    private PjsWebUser() {
    }
}
