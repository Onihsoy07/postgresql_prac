package com.test.postgresql_test.config;

import com.test.postgresql_test.config.jwt.JwtProperties;
import com.test.postgresql_test.config.jwt.TokenProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

//    @Bean
//    public TokenProvider tokenProvider(JwtProperties jwtProperties) {
//        return new TokenProvider(jwtProperties.getSecret(), jwtProperties.getTokenValidityInMilliseconds());
//    }
}
