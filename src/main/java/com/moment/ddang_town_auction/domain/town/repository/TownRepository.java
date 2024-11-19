package com.moment.ddang_town_auction.domain.town.repository;

import com.moment.ddang_town_auction.domain.town.entity.Town;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Long> {

    Optional<Town> findByName(String townName);

}
