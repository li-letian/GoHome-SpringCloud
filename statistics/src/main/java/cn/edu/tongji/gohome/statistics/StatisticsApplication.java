package cn.edu.tongji.gohome.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class StatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:8080",
                                "http://*.guisu.website",
                                "https://*.guisu.website"
                        )
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}