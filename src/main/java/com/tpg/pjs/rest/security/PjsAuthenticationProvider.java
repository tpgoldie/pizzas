package com.tpg.pjs.rest.security;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import static com.tpg.pjs.rest.security.WebRole.PJS_USER;
import static java.util.Collections.singletonList;

@Component
public class PjsAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String pwd = getPassword(authentication);

        UserAuthenticationDetails details = new UserAuthenticationDetails(username, pwd);

        PjsWebUser pjsWebUser = PjsWebUser.PJS_WEB_USER;

        if (details.match(pjsWebUser)) {
            return new UsernamePasswordAuthenticationToken(username, pwd, pjsWebUser.getAuthorities());
        }

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(pwd)) { return null; }

        return new UsernamePasswordAuthenticationToken(username, pwd, singletonList(new SimpleGrantedAuthority(PJS_USER.getRole())));
    }

    private String getPassword(Authentication authentication) {
        return authentication.getCredentials() != null ? authentication.getCredentials().toString() : "";
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    @Getter
    private static class UserAuthenticationDetails {

        private final String username;
        private final String password;

        UserAuthenticationDetails(String username, String password) {

            this.username = username;
            this.password = password;
        }

        public boolean match(PjsWebUser pjsWebUser) {
            return new EqualsBuilder().append(username, pjsWebUser.getUsername())
                    .append(password, pjsWebUser.getPassword()).isEquals();
        }
    }
}
