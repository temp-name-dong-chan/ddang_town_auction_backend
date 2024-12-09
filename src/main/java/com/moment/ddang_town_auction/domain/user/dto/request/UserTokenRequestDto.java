package com.moment.ddang_town_auction.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserTokenRequestDto {

    private String email;
    private Long townId;

}
