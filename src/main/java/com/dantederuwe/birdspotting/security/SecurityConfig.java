package com.dantederuwe.birdspotting.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().defaultSuccessUrl("/birdspotting",true);

        http.authorizeRequests()
                .antMatchers("/*").hasRole("SPOTTER")
                .antMatchers("/**/create-new-spotting").hasRole("ADMIN")
                .and().csrf();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("spotter")
                .password("{noop}RedDuck007")
                .roles("SPOTTER")
                .and()
                .withUser("admin")
                .password("{noop}eagle")
                .roles("SPOTTER","ADMIN");
    }
}