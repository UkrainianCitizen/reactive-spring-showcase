package ua.citizen.reactivespring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import ua.citizen.reactivespring.domain.Purchase;
import ua.citizen.reactivespring.service.PurchaseService;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {

    @Bean
    public RouterFunction<ServerResponse> routerFunctionPurchase(PurchaseService purchaseService) {
        return route(GET("/coin/purchase/v1/{id}"), request ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(purchaseService.findById(request.pathVariable("id")), Purchase.class));
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunctionAllPurchases(PurchaseService purchaseService) {
        return route(GET("/coin/purchase/v1/"), request ->
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(purchaseService.findAll(), Purchase.class));
    }
}