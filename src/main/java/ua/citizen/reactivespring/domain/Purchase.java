package ua.citizen.reactivespring.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
public class Purchase {

    private String name;
    private String price;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Id
    private String id;

    public Purchase(String id, String name, String price) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public Purchase(String name, String price) {
        this.name = name;
        this.price = price;
    }
}