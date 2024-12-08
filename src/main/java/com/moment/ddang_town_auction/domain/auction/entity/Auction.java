package com.moment.ddang_town_auction.domain.auction.entity;

import com.moment.ddang_town_auction.domain.auction.dto.request.AuctionCreateRequestDto;
import com.moment.ddang_town_auction.domain.user.entity.User;
import com.moment.ddang_town_auction.global.common.entity.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE auctions SET deleted_at = CONVERT_TZ(NOW(), @@session.time_zone, '+09:00') WHERE id = ?")
@SQLRestriction(value = "deleted_at IS NULL")
@Entity
@Table(name = "auctions")
public class Auction extends Timestamp {

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
