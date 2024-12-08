package com.moment.ddang_town_auction.domain.town.entity;

import com.moment.ddang_town_auction.global.common.entity.Timestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "towns")
public class Town extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double x;

    @Column(nullable = false)
    private Double y;

    public Town(String name, Double x, Double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
