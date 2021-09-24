package com.maiaaraujo5.bookclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class BookclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookclassApplication.class, args);
    }

}
