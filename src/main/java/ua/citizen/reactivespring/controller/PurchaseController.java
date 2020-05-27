package ua.citizen.reactivespring.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ua.citizen.reactivespring.domain.Purchase;
import ua.citizen.reactivespring.service.PurchaseService;

@RestController("/coin/purchase/v1")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/{name}")
    public Mono<Purchase> createPurchase(@PathVariable("name") String name) {
        return purchaseService.createPurchase(name);
    }
}