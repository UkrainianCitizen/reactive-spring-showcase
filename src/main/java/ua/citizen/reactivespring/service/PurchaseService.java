package ua.citizen.reactivespring.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.citizen.reactivespring.domain.Purchase;

public interface PurchaseService {
    Mono<Purchase> getPurchaseById(String purchaseId);
    Flux<Purchase> listAll();
    Mono<Purchase> createPurchase(String priceName);
}