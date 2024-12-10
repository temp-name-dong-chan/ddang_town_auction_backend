package com.moment.ddang_town_auction.domain.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuctionServiceTest {

    @Autowired
    private AuctionService auctionService;

//    /*
//    dump data를 위한 테스트코드
//     */
//    @Test
//    public void createDumpData() {
//        for (int i = 1; i <= 20; i++) {
//            AuctionCreateRequestDto auctionCreateRequestDto = new AuctionCreateRequestDto(
//                    "testTitle" + i,
//                    "testDescription" + i
//            );
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    "qwer@qwer",
//                    null
//            );
//
//            auctionService.createAuction(auctionCreateRequestDto, authentication);
//        }
//
//        for (int i = 1; i <= 20; i++) {
//            AuctionCreateRequestDto auctionCreateRequestDto = new AuctionCreateRequestDto(
//                    "testTitle" + i,
//                    "testDescription" + i
//            );
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    "qwer@qwer",
//                    null
//            );
//
//            auctionService.createAuction(auctionCreateRequestDto, authentication);
//        }
//    }

}