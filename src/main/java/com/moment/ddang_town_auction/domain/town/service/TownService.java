package com.moment.ddang_town_auction.domain.town.service;

import com.moment.ddang_town_auction.domain.town.entity.Town;
import com.moment.ddang_town_auction.domain.town.repository.TownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TownService {

    private final TownRepository townRepository;

    public Town getTownByName(String townName) {
        return townRepository.findByName(townName).orElseThrow(
            () -> new NullPointerException("해당하는 도시가 없습니다.")
        );
    }
}
