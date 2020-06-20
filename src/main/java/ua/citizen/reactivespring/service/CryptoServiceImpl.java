package ua.citizen.reactivespring.service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import ua.citizen.reactivespring.model.CoinBaseResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class CryptoServiceImpl implements CryptoService {

    private final WebClient webClient;

    @Override
    public Mono<CoinBaseResponse> getCryptoPrice(String currencyPair) {
        return webClient.get()
                // currency pair such as "BTC-USD"
                .uri("https://api.coinbase.com/v2/prices/{currency-pair}/buy", currencyPair)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(CoinBaseResponse.class));
    }
}