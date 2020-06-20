package ua.citizen.reactivespring.service;

import javax.xml.ws.Response;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.citizen.reactivespring.domain.Purchase;
import ua.citizen.reactivespring.model.CoinBaseResponse;

public interface PurchaseService {
	Mono<Purchase> doPurchase(String currencyPair, Mono<CoinBaseResponse> coinBaseResponse);
	Mono<Purchase> findById(String id);
	Flux<Purchase> findAll();
}