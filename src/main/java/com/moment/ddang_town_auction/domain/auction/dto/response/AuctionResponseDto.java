package com.moment.ddang_town_auction.domain.auction.dto.response;

import com.moment.ddang_town_auction.domain.auction.entity.Auction;
import com.moment.ddang_town_auction.domain.auction.entity.AuctionStatusEnum;
import lombok.Getter;

@Getter
public class AuctionResponseDto {

    private final Long auctionId;
    private final String title;
    private final String description;
    private final AuctionStatusEnum status;
    private final String authorName;
    private final String townName;

    public AuctionResponseDto(Auction auction) {
        this.auctionId = auction.getId();
        this.title = auction.getTitle();
        this.description = auction.getDescription();
        this.status = auction.getStatusEnum();
        this.authorName = auction.getUsername();
        this.townName = auction.getTownName();
    }
}
