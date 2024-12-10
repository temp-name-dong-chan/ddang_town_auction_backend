package com.moment.ddang_town_auction.domain.auction.dto.response;

import com.moment.ddang_town_auction.domain.auction.entity.Auction;
import lombok.Getter;

import java.util.List;

@Getter
public class AuctionsResponseDto {

    private final List<AuctionResponseDto> auctions;
    private final boolean hasMore;
    private final long lastAuctionId;

    public AuctionsResponseDto(List<Auction> auctions, boolean hasMore) {
        this.auctions = auctions.stream().map(AuctionResponseDto::new).toList();
        this.hasMore = hasMore;
        this.lastAuctionId = this.hasMore ? auctions.getLast().getId() : -1;
    }
}
