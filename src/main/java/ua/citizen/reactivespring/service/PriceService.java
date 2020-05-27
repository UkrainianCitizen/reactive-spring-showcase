package ua.citizen.reactivespring.service;

import reactor.core.publisher.Mono;
import ua.citizen.reactivespring.model.CoinBaseResponse;

public interface PriceService {
    Mono<CoinBaseResponse> getCryptoPrice(String priceName);
}