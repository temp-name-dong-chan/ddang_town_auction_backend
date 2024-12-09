package com.moment.ddang_town_auction.domain.auction.dto.response;

import com.moment.ddang_town_auction.domain.auction.entity.Auction;
import lombok.Getter;

import java.util.List;

@Getter
public class AuctionsResponseDto {

    private final int auctionsCount;
    private final List<AuctionResponseDto> auctions;

    public AuctionsResponseDto(List<Auction> auctions) {
        this.auctionsCount = auctions.size();
        this.auctions = auctions.stream().map(AuctionResponseDto::new).toList();
    }
}
