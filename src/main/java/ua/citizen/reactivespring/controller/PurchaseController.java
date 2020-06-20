package ua.citizen.reactivespring.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import ua.citizen.reactivespring.domain.Purchase;
import ua.citizen.reactivespring.service.CryptoService;
import ua.citizen.reactivespring.service.PurchaseService;

@RestController
@AllArgsConstructor
@RequestMapping("/coin/purchase/v1")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final CryptoService cryptoService;

    @PostMapping("/{currency_pair}")
    public Mono<Purchase> createPurchase(@PathVariable("currency_pair") String currencyPair) {
        return purchaseService.doPurchase(currencyPair, cryptoService.getCryptoPrice(currencyPair));
    }
}