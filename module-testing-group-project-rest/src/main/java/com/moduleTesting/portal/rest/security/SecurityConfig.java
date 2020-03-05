package com.moduleTesting.portal.rest.security;

import com.moduleTesting.portal.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

import static com.moduleTesting.portal.consts.Common.ROLE_ADMIN;
import static com.moduleTesting.portal.consts.Common.ROLE_DRIVER;
import static com.moduleTesting.portal.consts.RestNavigation.*;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authProvider;

    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${spring.security.origins.allow}")
    private String allowOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                    .addMapping("/**")
                    .allowedMethods("*")
                    .allowedHeaders("*")
                    .allowCredentials(false)
                    .allowedOrigins(allowOrigins);
            }
        };
    }

    @Autowired
    @Qualifier("oauth2ClientContext")
    private OAuth2ClientContext oAuth2ClientContext;

    @Bean
    @ConfigurationProperties("google.client")
    public AuthorizationCodeResourceDetails google()
    {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("google.resource")
    public ResourceServerProperties googleResource()
    {
        return new ResourceServerProperties();
    }

    @Bean
    public FilterRegistrationBean oAuth2ClientFilterRegistration(OAuth2ClientContextFilter oAuth2ClientContextFilter)
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(oAuth2ClientContextFilter);
        registration.setOrder(-100);
        return registration;
    }

    private Filter ssoFilter()
    {
        OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/google");
        OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(google(), oAuth2ClientContext);
        googleFilter.setRestTemplate(googleTemplate);
        CustomUserInfoTokenServices tokenServices = new CustomUserInfoTokenServices(googleResource().getUserInfoUri(), google().getClientId());
        tokenServices.setRestTemplate(googleTemplate);
        googleFilter.setTokenServices(tokenServices);
        tokenServices.setUserService(userService);
        tokenServices.setPasswordEncoder(bCryptPasswordEncoder);
        return googleFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
            .and()
                .httpBasic()
            .and()
                  .authorizeRequests()
                        .antMatchers(HttpMethod.POST, PATH_USER_SIGN_UP).anonymous()
                        .antMatchers(HttpMethod.PUT, PATH_USER_EDIT_ME).hasAuthority(ROLE_DRIVER)
                        .antMatchers(HttpMethod.GET, PATH_USER_GET_ME).hasAuthority(ROLE_DRIVER)
                        .antMatchers(HttpMethod.GET, PATH_USER_ALL).hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.GET, PATH_USER_ALL_DRIVERS).hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.GET, PATH_USER_GET_BY_ID).hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.GET, PATH_USER_GET_ADMIN).hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.PUT, PATH_USER_EDIT).hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.PUT, PATH_USER_CHANGE_STATUS).hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.DELETE, PATH_USER_DELETE).hasAuthority(ROLE_ADMIN)

                        .antMatchers(HttpMethod.GET, PATH_TASK_ALL_MINE).hasAuthority(ROLE_DRIVER)
                        .antMatchers(HttpMethod.GET, PATH_TASK_BY_STATUS).hasAuthority(ROLE_DRIVER)
                        .antMatchers(HttpMethod.GET, PATH_TASK_ALL).hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.PUT, PATH_TASK_CHANGE_TASK_TO_VALIDATED_STATUS).hasAuthority(ROLE_DRIVER)
                        .antMatchers(HttpMethod.PUT, PATH_TASK_CHANGE_TASK_TO_REJECTED_OR_FINISHED_STATUS).hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.PUT, PATH_TASK_TAKE_TASK).hasAnyAuthority(ROLE_DRIVER)
                        .antMatchers(HttpMethod.POST, PATH_TASK_CREATE_NEW).hasAuthority(ROLE_ADMIN)

                        .antMatchers(HttpMethod.GET, PATH_REPORT_GET_BY_TASK_ID).authenticated()
                        .antMatchers(HttpMethod.POST, PATH_REPORT_CREATE_REPORT).hasAuthority(ROLE_DRIVER)
                        .antMatchers(HttpMethod.GET, PATH_REPORT_ALL).hasAuthority(ROLE_ADMIN)

                        .antMatchers(HttpMethod.GET, PATH_CAR_ALL_FREE).hasAuthority(ROLE_DRIVER)
                        .antMatchers(HttpMethod.GET, PATH_CAR_ALL).hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.POST, PATH_CAR_ADD_NEW).hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.PUT, PATH_CAR_EDIT).hasAuthority(ROLE_ADMIN)
                        .antMatchers(HttpMethod.DELETE, PATH_CAR_REMOVE_BY_ID).hasAuthority(ROLE_ADMIN)

                        .antMatchers(HttpMethod.GET, PATH_BRAND_ALL).hasAuthority(ROLE_ADMIN)

                        .antMatchers(HttpMethod.GET, PATH_AUTH_AUTHENTICATE).anonymous();
                        //.antMatchers(HttpMethod.POST, PATH_USER_SIGN_UP).anonymous();

                        /* From my point of view it means that any request except all above will demand basic auth
                        Also this line disables object-info with timestamp, status, error, message, path -fields */
                        //.anyRequest().authenticated();

        http
            .addFilterBefore(ssoFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
