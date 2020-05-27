package ua.citizen.reactivespring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ua.citizen.reactivespring.model.CoinBaseResponse;
import ua.citizen.reactivespring.service.PriceService;

@RestController("/coin/price/v1")
public final class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/{name}")
    public Mono<CoinBaseResponse> getPrice(@PathVariable String name) {
        return priceService.getCryptoPrice(name);
    }
}