package com.karan.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route( r -> r
                        .path("/bank/accounts/**")
                        .filters(f -> f
                                .rewritePath("/bank/accounts/(?<segment>.*)", "/${segment}")
                                .addRequestHeader(
                                        "X-Response-Time",
                                        LocalDateTime.now().toString()
                                )
                        )
                        .uri("lb://accounts-service")
                )
                .route( r -> r
                        .path("/bank/cards/**")
                        .filters(f -> f
                                .rewritePath("/bank/cards/(?<segment>.*)", "/${segment}")
                                .addRequestHeader(
                                        "X-Response-Time",
                                        LocalDateTime.now().toString()
                                )
                        )
                        .uri("lb://cards-service")
                )
                .route( r -> r
                        .path("/bank/loans/**")
                        .filters(f -> f
                                .rewritePath("/bank/loans/(?<segment>.*)", "/${segment}")
                                .addRequestHeader(
                                        "X-Response-Time",
                                        LocalDateTime.now().toString()
                                )
                        )
                        .uri("lb://loans-service")
                )
                .build();
    }
}
