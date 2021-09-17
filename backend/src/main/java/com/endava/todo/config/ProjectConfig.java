package com.endava.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest().permitAll();

        http.cors(c -> {
            CorsConfigurationSource cs = r -> {
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowedHeaders(List.of("Content-Type", "Accept",
                        "X-Requested-With", "remember-me", "authorization", "x-auth-token"));
                cc.setAllowCredentials(true);
                cc.setAllowedOrigins(List.of("http://localhost:3000"));
                cc.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
                cc.setMaxAge(3600L);
                return cc;
            };

            c.configurationSource(cs);
        });
    }

}
