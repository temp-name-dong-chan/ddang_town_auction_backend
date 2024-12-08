package com.moment.ddang_town_auction.domain.auction.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuctionCreateRequestDto {

    @NotBlank(message = "제목은 필수로 입력해야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수로 입력해야 합니다.")
    private String description;

}
