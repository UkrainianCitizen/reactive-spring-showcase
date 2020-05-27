package ua.citizen.reactivespring.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ua.citizen.reactivespring.model.CoinBaseResponse;

@Service
public class PriceServiceImpl implements PriceService {

    private final WebClient webClient;

    public PriceServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<CoinBaseResponse> getCryptoPrice(String currencyPair) {
        return webClient.get()
                // currency pair such as "BTC-USD"
                .uri("https://api.coinbase.com/v2/prices/{currency-pair}/buy", currencyPair)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(CoinBaseResponse.class));
    }
}