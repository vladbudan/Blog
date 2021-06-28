package com.budan.springappblog.config;

import com.budan.springappblog.repository.UserRepository;
import com.budan.springappblog.security.service.AuthenticationHelper;
import com.budan.springappblog.security.service.JwtAuthenticationProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan({
        "com.budan.springappblog.service",
        "com.budan.springappblog.security.service",
        "com.budan.springappblog.config",
        "com.budan.springappblog.controller"
})
public class RootConfiguration {

    private UserRepository userRepository;

    @Bean
    public JwtAuthenticationProvider getAuthenticationProvider() {
        return new JwtAuthenticationProvider(userRepository, getAuthenticationHelper());
    }

    @Bean
    public AuthenticationHelper getAuthenticationHelper() {
        return new AuthenticationHelper(getObjectMapper());
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }


}
