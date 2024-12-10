package com.moment.ddang_town_auction.domain.auction.controller;

import com.moment.ddang_town_auction.domain.auction.dto.request.AuctionCreateRequestDto;
import com.moment.ddang_town_auction.domain.auction.dto.response.AuctionsResponseDto;
import com.moment.ddang_town_auction.domain.auction.service.AuctionService;
import com.moment.ddang_town_auction.domain.user.dto.response.UserAuthenticationToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/auctions")
@RestController
public class AuctionController {

    private final AuctionService auctionService;

    //    todo. sort 적용
    //    todo. searchFilter
    @GetMapping
    @Operation(summary = "이웃 경매글 조회")
    public ResponseEntity<AuctionsResponseDto> getAuctions(
            @RequestParam(required = false) Long lastAuctionId,
            @RequestParam(defaultValue = "10") int pageSize,
            Authentication authentication
    ) {
        UserAuthenticationToken userAuthenticationToken = (UserAuthenticationToken) authentication;
        AuctionsResponseDto auctionsResponseDto = auctionService.getAuctions(userAuthenticationToken, lastAuctionId, pageSize);
        return ResponseEntity.ok(auctionsResponseDto);
    }

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

//    todo. fileUpload api
}
