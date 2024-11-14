package com.moment.ddang_town_auction.global.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/health")
    @Operation(summary = "헬스체크")
    public String heathCheck() {
        return "ok";
    }

}
