package ua.citizen.reactivespring.service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.citizen.reactivespring.domain.PurchaseRepository;
import ua.citizen.reactivespring.domain.Purchase;
import ua.citizen.reactivespring.model.CoinBaseResponse;

@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

	private final PurchaseRepository repository;

	@Override
	public Mono<Purchase> doPurchase(String currencyPair, Mono<CoinBaseResponse> coinBaseResponse) {
		return coinBaseResponse.flatMap(response -> repository.save(preparePurchase(currencyPair, response)));
	}

	private Purchase preparePurchase(String priceName, CoinBaseResponse response) {
		return new Purchase(priceName, response.getData().getAmount());
	}

	@Override
	public Mono<Purchase> findById(String id) {
		return repository.findById(id);
	}

	@Override
	public Flux<Purchase> findAll() {
		return repository.findAll();
	}
}