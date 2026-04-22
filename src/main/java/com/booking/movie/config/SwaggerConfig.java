package com.booking.movie.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI bookingOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Movie Booking API")
                        .description("Backend APIs for movie ticket booking platform")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Booking Team")
                                .email("support@santosh.com")));
    }
}
