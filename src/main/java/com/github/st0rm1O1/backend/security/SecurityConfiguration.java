package com.github.st0rm1O1.backend.security;

import com.github.st0rm1O1.frontend.page.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry -> registry.requestMatchers(new AntPathRequestMatcher("/public/**")).permitAll());
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("alien")
                        .password("{noop}admin")
                        .roles("ADMIN")
                        .build(),
                User.withUsername("kunal")
                        .password("{noop}admin")
                        .roles("ADMIN")
                        .build()
        );
    }
}
