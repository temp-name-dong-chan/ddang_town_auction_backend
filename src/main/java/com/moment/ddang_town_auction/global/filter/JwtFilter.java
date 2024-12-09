package com.moment.ddang_town_auction.global.filter;

import com.moment.ddang_town_auction.domain.user.dto.response.UserAuthenticationToken;
import com.moment.ddang_town_auction.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j(topic = "JwtFilter")
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String token = jwtUtil.getJwtFromHeader(request);

        if (token == null) {
            log.error("토큰이 유효하지 않습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        if (jwtUtil.isExpired(token)) {
            log.error("토큰이 만료되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = jwtUtil.getClaimsFromToken(token);

        UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(
                claims.get("email", String.class),
                claims.get("townId", Long.class),
                null,
                List.of(new SimpleGrantedAuthority("USER"))
        );

        userAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);
        filterChain.doFilter(request, response);
    }

}
