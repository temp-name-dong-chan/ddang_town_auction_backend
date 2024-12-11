package com.moment.ddang_town_auction.domain.auction.repository;

import com.moment.ddang_town_auction.domain.auction.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Query(value = """
            SELECT *
            FROM auctions a
            WHERE a.town_id IN :townIds
                  AND (
                      (:sortOrder = 'asc' AND (:lastAuctionId IS NULL OR a.id > :lastAuctionId)) OR
                      (:sortOrder = 'desc' AND (:lastAuctionId IS NULL OR a.id < :lastAuctionId))
                  )
            ORDER BY 
                  CASE WHEN :sortOrder = 'asc' THEN a.id END ASC,
                  CASE WHEN :sortOrder = 'desc' THEN a.id END DESC
            LIMIT :pageSize
            """, nativeQuery = true)
    List<Auction> getAuctionsInTowns(
            @Param("townIds") List<Long> nearTownIds,
            @Param("lastAuctionId") Long lastAuctionId,
            @Param("pageSize") int pageSize,
            @Param("sortOrder") String sortOrder
    );

}
