package com.moment.ddang_town_auction.domain.auction.service;

import com.moment.ddang_town_auction.domain.auction.dto.request.AuctionCreateRequestDto;
import com.moment.ddang_town_auction.domain.auction.entity.Auction;
import com.moment.ddang_town_auction.domain.auction.repository.AuctionRepository;
import com.moment.ddang_town_auction.domain.user.entity.User;
import com.moment.ddang_town_auction.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final UserService userService;

    @Transactional
    public void createAuction(AuctionCreateRequestDto auctionCreateRequestDto, Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        Auction auction = new Auction(auctionCreateRequestDto, user);
        auctionRepository.save(auction);
    }
}
