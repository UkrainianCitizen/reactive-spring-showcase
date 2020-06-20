package ua.citizen.reactivespring.domain;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class DocumentTest {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    public void findByName() {
        Flux<Purchase> flux = purchaseRepository.deleteAll()
                .thenMany(prepareFlux())
                .thenMany(purchaseRepository.findByName("BTC2"));

        StepVerifier.create(flux)
                .thenConsumeWhile(prepareMatch())
                .verifyComplete();
    }

    private Predicate<Purchase> prepareMatch() {
        return purchase -> (purchase.getPrice().equalsIgnoreCase("1") ||
                purchase.getPrice().equalsIgnoreCase("5")) &&
                !purchase.getId().isEmpty();
    }

    private Flux<Purchase> prepareFlux() {
        return Flux.just(new Purchase("BTC", "2"), new Purchase("BTC2", "1"),
                new Purchase("BTC2", "5")
        )
                .flatMap(purchaseRepository::save);
    }
}