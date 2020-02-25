package com.moduleTesting.portal.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authProvider;

    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
            //.and()
                .authorizeRequests()
                //.antMatchers("/authenticate").permitAll()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //.antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user/allDrivers").hasAuthority("DRIVER")
                //.antMatchers(HttpMethod.POST, "/post/load/").hasAuthority("DRIVER")
                .anyRequest().authenticated()
            .and()
            .httpBasic();
    }

/*    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/authenticate");
    }*/



}
