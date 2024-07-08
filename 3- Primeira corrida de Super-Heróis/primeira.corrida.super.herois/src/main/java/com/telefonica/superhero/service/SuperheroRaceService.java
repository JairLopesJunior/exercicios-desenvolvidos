package com.telefonica.superhero.service;

import com.telefonica.superhero.rest.dto.RaceData;
import com.telefonica.superhero.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SuperheroRaceService {

    private ReaderLogService readerLogService;

    @Autowired
    public SuperheroRaceService(ReaderLogService readerLogService) {
        this.readerLogService = readerLogService;
    }

    public List<ResponseDTO> getData() {
        var raceDataList = this.readerLogService.getData("fileTest.txt");

        var groupedBySuperHero = raceDataList.stream()
                .collect(Collectors.groupingBy(RaceData::getSuperHero));

        RaceData bestLapRace = raceDataList.stream()
                .min(Comparator.comparing(RaceData::getLapTime))
                .get();

        var responseDTOS = this.getResponseDTO(groupedBySuperHero, bestLapRace);

        Collections.sort(responseDTOS,
                Comparator.comparingInt(ResponseDTO::getNumberLapsCompleted).reversed()
        );

        return this.getFinishingPosition(responseDTOS);
    }

    private List<ResponseDTO> getResponseDTO(Map<String, List<RaceData>> groupedBySuperHero, RaceData bestLapRace) {
        Integer backNumber = 0;
        ResponseDTO dto = new ResponseDTO();
        List<ResponseDTO> responseDTOS = new ArrayList<>();
        for (List<RaceData> list : groupedBySuperHero.values()) {
            for (RaceData hero : list) {
                dto = new ResponseDTO();
                if (hero.getSuperHero().contains("–") && hero.getSuperHero().split("–").length == 2) {
                    String[] codeName = hero.getSuperHero().split("–");
                    dto.setSuperHeroCode(Integer.valueOf(codeName[0]));
                    dto.setSuperHeroName(codeName[1]);
                }

                if (hero.getBackNumber() != null) {
                    dto.setNumberLapsCompleted(++backNumber);
                }
            }

            Duration totalDuration = list.stream()
                    .filter(r -> r.getLapTime() != null)
                    .map(obj -> Duration.between(LocalTime.MIDNIGHT, obj.getLapTime()))
                    .reduce(Duration.ZERO, Duration::plus);

            LocalTime totalTime = LocalTime.ofSecondOfDay(totalDuration.getSeconds());
            dto.setTotalTestTime(totalTime);

            DoubleSummaryStatistics statistics = list
                    .stream()
                    .collect(Collectors.summarizingDouble(RaceData::getAverageLapSpeed));

            Collections.sort(list,
                    Comparator.comparing(RaceData::getLapTime)
            );
            dto.setMediaSpeed(list.get(0).getLapTime());
            dto.setBestLapRace(bestLapRace.getLapTime());
            dto.setSuperheroAverageSpeedDuringRace(statistics.getAverage());
            responseDTOS.add(dto);
            backNumber = 0;
        }
        return responseDTOS;
    }

    private List<ResponseDTO> getFinishingPosition(List<ResponseDTO> responseDTOS) {
        for (int i = 0; i < responseDTOS.size(); i++) {
            responseDTOS.get(i).setFinishingPosition((i + 1));
        }
        return responseDTOS;
    }
}
