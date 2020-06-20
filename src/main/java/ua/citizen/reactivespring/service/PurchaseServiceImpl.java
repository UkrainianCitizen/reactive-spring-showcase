package ua.citizen.reactivespring.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.citizen.reactivespring.domain.PriceRepository;
import ua.citizen.reactivespring.domain.Purchase;
import ua.citizen.reactivespring.model.CoinBaseResponse;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PriceService priceService;
    private final PriceRepository priceRepository;

    @Override
    public Mono<Purchase> getPurchaseById(String purchaseId) {
        return priceRepository.findById(purchaseId);
    }

    @Override
    public Flux<Purchase> listAll() {
        return priceRepository.findAll();
    }

    @Override
    public Mono<Purchase> createPurchase(String currencyPair) {
        return priceService.getCryptoPrice(currencyPair).flatMap(price -> priceRepository.save(preparePurchase(currencyPair, price)));
    }

    private Purchase preparePurchase(String priceName, CoinBaseResponse response) {
        return new Purchase(priceName, response.getData().getAmount());
    }
}