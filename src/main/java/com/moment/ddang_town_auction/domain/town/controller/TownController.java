package com.moment.ddang_town_auction.domain.town.controller;

import com.moment.ddang_town_auction.domain.town.entity.Town;
import com.moment.ddang_town_auction.domain.town.service.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/towns")
@RestController
public class TownController {

    private final TownService townService;

    @GetMapping("near")
    public List<Town> getNearTowns(
            @RequestParam(required = false) String townName
    ) {
        return townService.getNearTowns(townName);
    }
}