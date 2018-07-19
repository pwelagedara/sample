package com.devopstools.portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.sql.DataSource;

/**
 * Created by pubudu on 8/14/17.
 */
@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomBasicAuthenticationEntrypoint customBasicAuthenticationEntrypoint;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .authenticationEntryPoint(customBasicAuthenticationEntrypoint) // Custom 401 Responses
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()) // Custom 403 Responses
                .and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/sample-controller/protected/**").permitAll() // Allow Preflight Check
                .antMatchers("/sample-controller/protected/**").hasRole("ADMIN") // Verify Role for the Protected Endpoint
                .antMatchers("**/v2/**").permitAll() // Permit everything to Swagger Doc Endpoint
                .antMatchers("/sample-web-controller/**").permitAll() // Permit everything to Web Pages
                .and()
                .csrf().disable() // Disable CSRF
                .headers().frameOptions().disable() // Disable X-Frame-Options Header
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Disable Session Creation
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users  WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username=?");
    }
}
