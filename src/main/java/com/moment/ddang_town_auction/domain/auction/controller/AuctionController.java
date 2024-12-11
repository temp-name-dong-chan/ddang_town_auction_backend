package com.moment.ddang_town_auction.domain.auction.controller;

import com.moment.ddang_town_auction.domain.auction.dto.request.AuctionCreateRequestDto;
import com.moment.ddang_town_auction.domain.auction.dto.response.AuctionsResponseDto;
import com.moment.ddang_town_auction.domain.auction.service.AuctionService;
import com.moment.ddang_town_auction.domain.user.dto.response.UserAuthenticationToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
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

    //    todo. params에 대한 validator 설정
    //    todo. params에 대한 swagger 설정
    //    todo. searchFilter
    @GetMapping
    @Operation(summary = "이웃 경매글 조회")
    public ResponseEntity<AuctionsResponseDto> getAuctions(
            @RequestParam(required = false) @Min(value = 1, message = "경매 아이디는 1 이상입니다.") Long lastAuctionId,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "desc") @Pattern(regexp = "asc|desc", message = "asc 혹은 desc만 입력이 가능합니다.") String sortOrder,
            UserAuthenticationToken userAuthenticationToken
    ) {
        AuctionsResponseDto auctionsResponseDto = auctionService.getAuctions(
                userAuthenticationToken,
                lastAuctionId,
                pageSize,
                sortOrder
        );
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
