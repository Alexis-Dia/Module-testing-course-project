package com.moduleTesting.portal.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.moduleTesting.portal.consts.Common.ROLE_ADMIN;
import static com.moduleTesting.portal.consts.Common.ROLE_DRIVER;
import static com.moduleTesting.portal.consts.RestNavigation.PATH_USER_ALL_DRIVERS;

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
            .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/user/new").anonymous()
                    .antMatchers(HttpMethod.PUT, "/user/editMe").hasAuthority(ROLE_DRIVER)
                    .antMatchers(HttpMethod.GET, "/user/getMe").hasAuthority(ROLE_DRIVER)
                    .antMatchers(HttpMethod.GET, "/user/all").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.GET, PATH_USER_ALL_DRIVERS).hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.GET, "/user/getById").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.GET, "/user/getAdmin").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.PUT, "/user/edit").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.PUT, "/user/changeStatus").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.DELETE, "/user/delete").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.PUT, "/user/transferMoney").hasAuthority(ROLE_ADMIN)

                    .antMatchers(HttpMethod.GET, "/task/allMine").hasAuthority(ROLE_DRIVER)
                    .antMatchers(HttpMethod.GET, "/task/all").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.GET, "/task/allActive").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.PUT, "/task/changeTaskStatus").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.POST, "/task/createNew").hasAuthority(ROLE_ADMIN)

                    .antMatchers(HttpMethod.GET, "/report/getByTaskId").authenticated()
                    .antMatchers(HttpMethod.POST, "/report/createReport").hasAuthority(ROLE_DRIVER)
                    .antMatchers(HttpMethod.GET, "/report/all").hasAuthority(ROLE_ADMIN)

                    .antMatchers(HttpMethod.GET, "/car/allFree").hasAuthority(ROLE_DRIVER)
                    .antMatchers(HttpMethod.GET, "/car/all").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.POST, "/car/addNew").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.PUT, "/car/edit").hasAuthority(ROLE_ADMIN)
                    .antMatchers(HttpMethod.DELETE, "/car/removeById").hasAuthority(ROLE_ADMIN)


                    .anyRequest().authenticated();

    }

}
