package com.moment.ddang_town_auction.domain.town.service;

import com.moment.ddang_town_auction.domain.town.entity.Town;
import com.moment.ddang_town_auction.domain.town.repository.TownRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j(topic = "TownService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TownService {

    private final static double defaultDistance = 0.01;

    private final TownRepository townRepository;

    public Town getTownByName(String townName) {
        return townRepository.findByName(townName).orElseThrow(
                () -> new NullPointerException("해당하는 도시가 없습니다.")
        );
    }

    public List<Town> getNearTowns(String townName) {
        Town town = getTownByName(townName);
        return townRepository.getNearTowns(town.getX(), town.getY(), defaultDistance);
    }

//    /*
//    excelDirectory에서 행정구역 db를 구축하는 코드.
//    일회성 사용
//     */
//    private final static String excelDirectory = "src/main/resources/static/AdminDistrictExcel.xlsx";
//
//    @Transactional
//    public void createTownAll() {
//        try {
//            FileInputStream file = new FileInputStream(excelDirectory);
//            List<Town> townList = new ArrayList<>();
//            Workbook workbook = WorkbookFactory.create(file);
//            int sheets = workbook.getNumberOfSheets();
//
//            for (int i = 0; i < sheets; i++) {
//                Sheet sheet = workbook.getSheetAt(i);
//                addTownFromFile(townList, sheet);
//            }
//
//            townRepository.saveAll(townList);
//        } catch (IOException | EncryptedDocumentException e) {
//            log.error(e.getMessage());
//        }
//    }
//
//    @Transactional
//    public void createTownSeoulAndGyeonggi() {
//        try {
//            FileInputStream file = new FileInputStream(excelDirectory);
//            List<Town> townList = new ArrayList<>();
//            Workbook workbook = WorkbookFactory.create(file);
//
//            Sheet sheet = workbook.getSheetAt(0);
//            addTownFromFile(townList, sheet);
//
//            sheet = workbook.getSheetAt(2);
//            addTownFromFile(townList, sheet);
//
//            townRepository.saveAll(townList);
//        } catch (IOException | EncryptedDocumentException e) {
//            log.error(e.getMessage());
//        }
//    }
//
//    private void addTownFromFile(List<Town> townList, Sheet sheet) {
//        for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
//            Row row = sheet.getRow(j);
//            StringBuilder townName = new StringBuilder();
//
//            for (int i = 0; i < 5; i++) {
//                String str = row.getCell(i).getStringCellValue();
//
//                if (!str.isEmpty()) {
//                    townName.append(str);
//                    townName.append(" ");
//                }
//            }
//
//            if(townName.substring(townName.length() - 1).equals(" ")){
//                townName.deleteCharAt(townName.length() - 1);
//            }
//
//            Town town = new Town(
//                    townName.toString(),
//                    row.getCell(5).getNumericCellValue(),
//                    row.getCell(6).getNumericCellValue()
//            );
//
//            townList.add(town);
//        }
//    }
}
