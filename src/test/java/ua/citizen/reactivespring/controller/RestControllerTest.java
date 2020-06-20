package ua.citizen.reactivespring.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ua.citizen.reactivespring.domain.Purchase;
import ua.citizen.reactivespring.service.CryptoService;
import ua.citizen.reactivespring.service.PurchaseService;

@WebFluxTest
public class RestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PurchaseService purchaseService;

    @MockBean
    private CryptoService cryptoService;

    @Test
    public void createPurchase() {
        Mockito.when(purchaseService.findAll())
                .thenReturn(Flux.just(new Purchase("test", "3"),
                        new Purchase("test2", "8")));


        webTestClient.get()
                .uri("http://localhost:8080/coin/purchase/v1/")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("@.[0].name").isEqualTo("test")
                .jsonPath("@.[0].price").isEqualTo("3")
                .jsonPath("@.[1].name").isEqualTo("test2")
                .jsonPath("@.[1].price").isEqualTo("8");
    }
}