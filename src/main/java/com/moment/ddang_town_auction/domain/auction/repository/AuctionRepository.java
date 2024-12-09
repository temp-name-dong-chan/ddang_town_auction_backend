package com.moment.ddang_town_auction.domain.auction.repository;

import com.moment.ddang_town_auction.domain.auction.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Query("SELECT a FROM Auction a WHERE a.town.id IN :townIds")
    List<Auction> getAuctionsInTowns(@Param("townIds") List<Long> nearTownIds);

}
