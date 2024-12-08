package com.moment.ddang_town_auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DdangTownAuctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdangTownAuctionApplication.class, args);
    }

}
