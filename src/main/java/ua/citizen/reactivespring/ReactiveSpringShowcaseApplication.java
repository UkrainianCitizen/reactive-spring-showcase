package ua.citizen.reactivespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "ua.citizen.reactivespring.config",
        "ua.citizen.reactivespring.controller"
})
public class ReactiveSpringShowcaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveSpringShowcaseApplication.class, args);
    }
}