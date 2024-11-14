package com.moment.ddang_town_auction.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserSigninRequestDto {

    @NotBlank(message = "이메일은 필수로 입력해야 합니다.")
    @Email(message = "이메일 형식에 맞지 않습니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    private String password;
}
