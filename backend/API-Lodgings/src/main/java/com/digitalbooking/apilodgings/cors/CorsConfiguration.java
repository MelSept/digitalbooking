package com.digitalbooking.apilodgings.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

    /**
     * Method to configure the application cors
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    // TODO: Move to class Spring Security
    /*
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource sources = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedOriginPatterns(List.of("*"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.addAllowedMethod("*");
        sources.registerCorsConfiguration("/**", corsConfig);
        return new CorsFilter(sources);
    }
     */
}
