package com.example.demo;

import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = "ADMIN";
    private static final String REGISTERED_USER = "REGISTERED_USER";
    private static final String GUEST = "GUEST";

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**")
                .permitAll()
                .and()
                .authorizeRequests()
                // user
                .antMatchers(HttpMethod.POST, "/api/v1/users").hasAnyAuthority(ADMIN, GUEST)
                .antMatchers(HttpMethod.PUT, "/api/v1/users").hasAnyAuthority(ADMIN, REGISTERED_USER)
                .antMatchers(HttpMethod.DELETE, "/api/v1/users/{id}").hasAnyAuthority(ADMIN, REGISTERED_USER)
                .antMatchers(HttpMethod.GET, "/api/v1/users/{id}").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.GET, "/api/v1/users/all").hasAuthority(ADMIN)
                // hall
                .antMatchers(HttpMethod.POST, "/api/v1/halls").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.PUT, "/api/v1/halls").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/api/v1/halls/{id}").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.GET, "/api/v1/halls/{id}").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.GET, "/api/v1/halls/all").hasAuthority(ADMIN)
                // movie
                .antMatchers(HttpMethod.POST, "/api/v1/movies").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.PUT, "/api/v1/movies").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/api/v1/movies/{id}").hasAnyAuthority(ADMIN)
                .antMatchers(HttpMethod.GET, "/api/v1/movies/{id}").hasAnyAuthority(ADMIN, REGISTERED_USER, GUEST)
                .antMatchers(HttpMethod.GET, "/api/v1/movies/all").hasAnyAuthority(ADMIN, REGISTERED_USER, GUEST)
                .antMatchers(HttpMethod.GET, "/api/v1/movies/rating").hasAnyAuthority(ADMIN, REGISTERED_USER, GUEST)
                // ticket
                .antMatchers(HttpMethod.POST, "/api/v1/tickets").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.PUT, "/api/v1/tickets").hasAnyAuthority(ADMIN, REGISTERED_USER) // REGISTERED_USER - to buy ticket
                .antMatchers(HttpMethod.DELETE, "/api/v1/tickets/{id}").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.GET, "/api/v1/tickets/{id}").hasAnyAuthority(ADMIN, REGISTERED_USER)
                .antMatchers(HttpMethod.GET, "/api/v1/tickets/all").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.GET, "/api/v1/tickets/details").hasAnyAuthority(ADMIN, REGISTERED_USER)
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder
                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("password1"))
                .authorities(ADMIN)
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("password2"))
                .authorities(REGISTERED_USER)
                .and()
                .withUser("guest")
                .password(passwordEncoder().encode("password3"))
                .authorities(GUEST);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}