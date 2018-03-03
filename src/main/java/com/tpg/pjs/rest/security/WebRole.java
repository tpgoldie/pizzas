package com.tpg.pjs.rest.security;

import lombok.Getter;

@Getter
public enum WebRole {

    GUEST("PJS_GUEST"), PJS_USER("PJS_USER");

    private final String role;

    WebRole(String value) {

        this.role = value;
    }
}
