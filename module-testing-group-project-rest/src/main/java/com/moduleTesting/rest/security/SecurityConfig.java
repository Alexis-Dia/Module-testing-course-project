package com.moduleTesting.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Autowired
    private DataSource dataSource;*/

    @Autowired
    private UserDetailsServiceImplementation userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/").permitAll()
                //.antMatchers("/first").hasAuthority("Service_Center")
                .antMatchers("/login").permitAll()
                .antMatchers("/post/load").permitAll()
                .anyRequest().fullyAuthenticated()
                .and().formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/default")
                .failureUrl("/login?error").permitAll()
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).permitAll();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService);

    }

}
