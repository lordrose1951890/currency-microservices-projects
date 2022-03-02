package org.lordrose.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        // "/conversion/(?<segment>.*)", "/currency-conversion/feign/${segment}" => better regex for all urls

        return builder.routes()
                .route(spec -> spec.path("/get")
                        .filters(request -> request.addRequestHeader("MyHeader", "MyUri")
                                .addRequestParameter("MyParam", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(spec -> spec.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route(spec -> spec.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(spec -> spec.path("/currency-conversion/feign/**")
                        .uri("lb://currency-conversion"))
                .route(spec -> spec.path("/conversion/**")
                        .filters(request -> request.rewritePath(
                                "/conversion/",
                                "/currency-conversion/feign/"))
                        .uri("lb://currency-conversion"))
                .build();
    }
}
