package com.moduleTesting.portal.rest.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MyAuthenticationManager implements AuthenticationManager {

    private final UserDetailsService userDetailsService;

    public MyAuthenticationManager(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    private static final String ROLE_DRIVER = "DRIVER";
    private static final String BAD_CREDENTIALS = "Bad Credentials";

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority(ROLE_DRIVER));
    }

    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        UserDetails userDetails =  userDetailsService.loadUserByUsername(auth.getName());

        if (Objects.isNull(userDetails)) {
            throw new BadCredentialsException(BAD_CREDENTIALS);
        }

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("DRIVER")));
    }
}
