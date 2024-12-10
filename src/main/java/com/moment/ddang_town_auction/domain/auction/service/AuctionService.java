package com.moment.ddang_town_auction.domain.auction.service;

import com.moment.ddang_town_auction.domain.auction.dto.request.AuctionCreateRequestDto;
import com.moment.ddang_town_auction.domain.auction.dto.response.AuctionsResponseDto;
import com.moment.ddang_town_auction.domain.auction.entity.Auction;
import com.moment.ddang_town_auction.domain.auction.repository.AuctionRepository;
import com.moment.ddang_town_auction.domain.town.service.TownService;
import com.moment.ddang_town_auction.domain.user.dto.response.UserAuthenticationToken;
import com.moment.ddang_town_auction.domain.user.entity.User;
import com.moment.ddang_town_auction.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final UserService userService;
    private final TownService townService;

    public AuctionsResponseDto getAuctions(
            UserAuthenticationToken userAuthenticationToken,
            Long lastAuctionId,
            int pageSize
    ) {
        List<Long> nearTownIds = townService.getNearTownIds(userAuthenticationToken.getTownId());
        Pageable pageable = PageRequest.of(0, pageSize + 1);
        List<Auction> auctions = auctionRepository.getAuctionsInTowns(nearTownIds, lastAuctionId, pageable);
        boolean hasMore = auctions.size() > pageSize;

        if (hasMore) {
            auctions = auctions.subList(0, pageSize);
        }

        return new AuctionsResponseDto(auctions, hasMore);
    }

    @Transactional
    public void createAuction(AuctionCreateRequestDto auctionCreateRequestDto, Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        Auction auction = new Auction(auctionCreateRequestDto, user);
        auctionRepository.save(auction);
    }
}
