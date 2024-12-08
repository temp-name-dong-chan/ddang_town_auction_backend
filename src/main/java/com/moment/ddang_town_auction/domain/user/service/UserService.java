package com.moment.ddang_town_auction.domain.user.service;

import com.moment.ddang_town_auction.domain.town.dto.request.TownRequestDto;
import com.moment.ddang_town_auction.domain.town.entity.Town;
import com.moment.ddang_town_auction.domain.town.service.TownService;
import com.moment.ddang_town_auction.domain.user.dto.request.UserRefreshRequestDto;
import com.moment.ddang_town_auction.domain.user.dto.request.UserSigninRequestDto;
import com.moment.ddang_town_auction.domain.user.dto.request.UserSignupRequestDto;
import com.moment.ddang_town_auction.domain.user.dto.response.UserSigninResponseDto;
import com.moment.ddang_town_auction.domain.user.entity.User;
import com.moment.ddang_town_auction.domain.user.repository.UserRepository;
import com.moment.ddang_town_auction.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final static Long ACCESS_TOKEN_EXPIRED_MS = 1000 * 60 * 30L; // 0.5 hour
    private final static Long REFRESH_TOKEN_EXPIRED_MS = 1000 * 60 * 60 * 24 * 7L; // 1 day

    private final UserRepository userRepository;
    private final TownService townService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserSignupRequestDto userSignupRequestDto) {
        validateEmail(userSignupRequestDto.getEmail());
        validateNickname(userSignupRequestDto.getNickname());
        validatePassword(
                userSignupRequestDto.getPassword(),
                userSignupRequestDto.getPasswordConfirm()
        );
        String password = passwordEncoder.encode(userSignupRequestDto.getPassword());

        userRepository.save(
                new User(
                        userSignupRequestDto.getEmail(),
                        userSignupRequestDto.getNickname(),
                        password
                )
        );

    }

    public UserSigninResponseDto signin(UserSigninRequestDto userSigninRequestDto) {
        User user = getUserByEmail(userSigninRequestDto.getEmail());

        if (!passwordEncoder.matches(userSigninRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createJwt(user.getEmail(), ACCESS_TOKEN_EXPIRED_MS);
        String refreshToken = jwtUtil.createJwt(user.getEmail(), REFRESH_TOKEN_EXPIRED_MS);

        return new UserSigninResponseDto(accessToken, refreshToken);
    }

    public UserSigninResponseDto refresh(
            UserRefreshRequestDto userRefreshRequestDto
    ) {
        String email = jwtUtil.getUsernameFromToken(
                userRefreshRequestDto.getRefreshToken()
        );
        User user = getUserByEmail(email);
        String accessToken = jwtUtil.createJwt(user.getEmail(), ACCESS_TOKEN_EXPIRED_MS);
        return new UserSigninResponseDto(accessToken, userRefreshRequestDto.getRefreshToken());
    }

    @Transactional
    public void setUserTown(
            TownRequestDto townRequestDto,
            Authentication authentication
    ) {
        User user = getUserByEmail(authentication.getName());
        Town town = townService.getTown(townRequestDto);
        user.updateTown(town);
    }

    private void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("동일한 이메일이 존재합니다.");
        }
    }

    private void validateNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("동일한 닉네임이 존재합니다.");
        }
    }

    private void validatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new NullPointerException("해당하는 유저가 없습니다.")
        );
    }

}
