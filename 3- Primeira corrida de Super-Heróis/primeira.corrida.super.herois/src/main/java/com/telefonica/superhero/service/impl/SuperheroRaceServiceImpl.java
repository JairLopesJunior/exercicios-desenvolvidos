package com.telefonica.superhero.service.impl;

import com.telefonica.superhero.rest.dto.RaceData;
import com.telefonica.superhero.rest.dto.ResponseDTO;
import com.telefonica.superhero.service.SuperheroRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SuperheroRaceServiceImpl implements SuperheroRaceService {

    private static final Integer ZERO = 0;

    private static final Integer ONE = 1;

    private static final Integer TWO = 2;

    private static final String FILE_NAME = "file.txt";

    private static final String HYPHEN = "–";

    private ReaderLogServiceImpl readerLogService;

    @Autowired
    public SuperheroRaceServiceImpl(ReaderLogServiceImpl readerLogService) {
        this.readerLogService = readerLogService;
    }

    public List<ResponseDTO> getData() {
        var raceDataList = this.readerLogService.getData(FILE_NAME);

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
        Integer backNumber = ZERO;
        ResponseDTO dto = new ResponseDTO();
        List<ResponseDTO> responseDTOS = new ArrayList<>();
        for (List<RaceData> list : groupedBySuperHero.values()) {
            for (RaceData hero : list) {
                dto = new ResponseDTO();
                if (hero.getSuperHero().contains(HYPHEN) && hero.getSuperHero().split(HYPHEN).length == TWO) {
                    String[] codeName = hero.getSuperHero().split(HYPHEN);
                    dto.setSuperHeroCode(Integer.valueOf(codeName[ZERO]));
                    dto.setSuperHeroName(codeName[ONE]);
                }

                if (hero.getBackNumber() != null) {
                    dto.setNumberLapsCompleted(++backNumber);
                }
            }

            Duration totalDuration = this.getTotalDuration(list);

            LocalTime totalTime = LocalTime.ofSecondOfDay(totalDuration.getSeconds());
            dto.setTotalTestTime(totalTime);

            DoubleSummaryStatistics statistics = list
                    .stream()
                    .collect(Collectors.summarizingDouble(RaceData::getAverageLapSpeed));

            Collections.sort(list,
                    Comparator.comparing(RaceData::getLapTime)
            );
            dto.setMediaSpeed(list.get(ZERO).getLapTime());
            dto.setBestLapRace(bestLapRace.getLapTime());
            dto.setSuperheroAverageSpeedDuringRace(statistics.getAverage());
            responseDTOS.add(dto);
            backNumber = ZERO;
        }
        return responseDTOS;
    }

    private List<ResponseDTO> getFinishingPosition(List<ResponseDTO> responseDTOS) {
        for (int i = ZERO; i < responseDTOS.size(); i++) {
            responseDTOS.get(i).setFinishingPosition((i + ONE));
        }
        return responseDTOS;
    }

    private Duration getTotalDuration(List<RaceData> list) {
        return list.stream()
                .filter(r -> r.getLapTime() != null)
                .map(obj -> Duration.between(LocalTime.MIDNIGHT, obj.getLapTime()))
                .reduce(Duration.ZERO, Duration::plus);
    }
}
