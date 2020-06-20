package ua.citizen.reactivespring.config;

import ua.citizen.reactivespring.domain.PurchaseRepository;
import ua.citizen.reactivespring.service.CryptoService;
import ua.citizen.reactivespring.service.CryptoServiceImpl;
import ua.citizen.reactivespring.service.PurchaseService;
import ua.citizen.reactivespring.service.PurchaseServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiConfig {

    @Bean
    public PurchaseService priceService(PurchaseRepository repository) {
        return new PurchaseServiceImpl(repository);
    }

    @Bean
    public CryptoService cryptoService() {
        return new CryptoServiceImpl(webClient());
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}