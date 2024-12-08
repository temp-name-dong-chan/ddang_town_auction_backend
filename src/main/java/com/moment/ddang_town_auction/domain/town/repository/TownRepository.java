package com.moment.ddang_town_auction.domain.town.repository;

import com.moment.ddang_town_auction.domain.town.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TownRepository extends JpaRepository<Town, Long> {

    Optional<Town> findByName(String townName);

    @Query(value = """
                SELECT *
                FROM towns t1
                WHERE SQRT(POW(t1.x - :x, 2) + POW(t1.y - :y, 2)) <= :defaultDistance
            """, nativeQuery = true)
    List<Town> getNearTowns(
            @Param("x") double x,
            @Param("y") double y,
            @Param("defaultDistance") double defaultDistance
    );

}
