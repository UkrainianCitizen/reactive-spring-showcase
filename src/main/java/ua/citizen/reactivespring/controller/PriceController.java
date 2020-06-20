package ua.citizen.reactivespring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import ua.citizen.reactivespring.model.CoinBaseResponse;
import ua.citizen.reactivespring.service.CryptoService;

@RestController("/coin/price/v1")
@AllArgsConstructor
public final class PriceController {

    private final CryptoService cryptoService;

    @GetMapping("/{name}")
    public Mono<CoinBaseResponse> getPrice(@PathVariable String name) {
        return cryptoService.getCryptoPrice(name);
    }
}