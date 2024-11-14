package com.moment.ddang_town_auction.domain.user.dto.response;

import lombok.Getter;

@Getter
public class UserSigninResponseDto {

    private final String accessToken;
    private final String refreshToken;
    private final String tokenType;

    public UserSigninResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = "Bearer ";
    }
}
