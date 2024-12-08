package com.moment.ddang_town_auction.domain.auction.controller;

import com.moment.ddang_town_auction.domain.auction.dto.request.AuctionCreateRequestDto;
import com.moment.ddang_town_auction.domain.auction.service.AuctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auctions")
@RestController
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping
    @Operation(summary = "경매글 생성")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Void> createAuction(
            @Valid @RequestBody AuctionCreateRequestDto auctionCreateRequestDto,
            Authentication authentication
    ) {
        auctionService.createAuction(auctionCreateRequestDto, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
