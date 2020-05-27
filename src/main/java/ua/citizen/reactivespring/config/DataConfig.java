package ua.citizen.reactivespring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories("ua.citizen.reactivespring.domain")
@Configuration
public class DataConfig  {

}