package com.sie.demo.security.config;

/**
 * @author Ivan
 * @description:
 * @date 2020-08-03 16:28:48
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    private static String[] originAllow = new String[]{
            "localhost:8081","127.0.0.1:8081",
            "localhost:8080","127.0.0.1:8080"
    };

    private void addAllowOrigins(CorsConfiguration config){
        for (String origin : originAllow){
            config.addAllowedOrigin("http://"+origin);
            config.addAllowedOrigin("https://"+origin);
        }
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        this.addAllowOrigins(config);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }

}
