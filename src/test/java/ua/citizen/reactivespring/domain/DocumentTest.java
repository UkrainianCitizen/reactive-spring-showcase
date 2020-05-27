package ua.citizen.reactivespring.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import java.util.function.Predicate;

@DataMongoTest
public class DocumentTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    public void findByName() {
        Flux<Purchase> flux = priceRepository.deleteAll()
                .thenMany(prepareFlux())
                .thenMany(priceRepository.findByName("BTC2"));

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
                .flatMap(priceRepository::save);
    }
}
