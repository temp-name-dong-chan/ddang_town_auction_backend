package com.moment.ddang_town_auction.domain.user.controller;

import com.moment.ddang_town_auction.domain.user.dto.request.UserRefreshRequestDto;
import com.moment.ddang_town_auction.domain.user.dto.request.UserSigninRequestDto;
import com.moment.ddang_town_auction.domain.user.dto.request.UserSignupRequestDto;
import com.moment.ddang_town_auction.domain.user.dto.response.UserSigninResponseDto;
import com.moment.ddang_town_auction.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Void> signup(
        @Valid @RequestBody UserSignupRequestDto userSignupRequestDto
    ) {
        userService.signup(userSignupRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    @Operation(summary = "로그인")
    public ResponseEntity<UserSigninResponseDto> signin(
        @Valid @RequestBody UserSigninRequestDto userSigninRequestDto
    ) {
        UserSigninResponseDto userSigninResponseDto = userService.signin(userSigninRequestDto);
        return ResponseEntity.ok(userSigninResponseDto);
    }

    @PostMapping("/refresh")
    @Operation(summary = "리프레시 토큰")
    public ResponseEntity<UserSigninResponseDto> refresh(
        @RequestBody UserRefreshRequestDto userRefreshRequestDto
    ) {
        UserSigninResponseDto userSigninResponseDto = userService.refresh(userRefreshRequestDto);
        return ResponseEntity.ok(userSigninResponseDto);
    }

    @GetMapping("users/info")
    @Operation(summary = "유저 정보 조회")
    public ResponseEntity<String> getUserInfo(
        Authentication authentication
    ) {
        return ResponseEntity.ok(authentication.getName());
    }

}
