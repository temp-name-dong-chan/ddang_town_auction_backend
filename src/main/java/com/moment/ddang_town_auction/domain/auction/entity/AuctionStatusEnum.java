package com.moment.ddang_town_auction.domain.auction.entity;

import lombok.Getter;

@Getter
public enum AuctionStatusEnum {

    ON_SALE("ON_SALE"),
    HOLD("HOLD"),
    COMPLETED("COMPLETED");

    private final String description;

    AuctionStatusEnum(String description) {
        this.description = description;
    }
}
