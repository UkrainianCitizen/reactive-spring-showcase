package ua.citizen.reactivespring.model;

import lombok.Data;

@Data
public class CoinBaseResponse {

    private Data data;

    @lombok.Data
    public static final class Data {
        private String base;
        private String currency;
        private String amount;
    }
}