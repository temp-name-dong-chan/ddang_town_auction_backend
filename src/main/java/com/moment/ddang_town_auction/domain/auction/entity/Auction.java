package com.moment.ddang_town_auction.domain.auction.entity;

import com.moment.ddang_town_auction.domain.auction.dto.request.AuctionCreateRequestDto;
import com.moment.ddang_town_auction.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Auction(AuctionCreateRequestDto auctionCreateRequestDto, User user) {
        this.title = auctionCreateRequestDto.getTitle();
        this.description = auctionCreateRequestDto.getDescription();
        this.user = user;
    }
}
