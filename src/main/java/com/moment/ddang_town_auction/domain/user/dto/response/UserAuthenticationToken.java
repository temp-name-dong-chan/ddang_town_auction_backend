package com.moment.ddang_town_auction.domain.user.dto.response;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class UserAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final Long townId;

    public UserAuthenticationToken(String email, Long townId, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(email, credentials, authorities);
        this.townId = townId;
    }
}
