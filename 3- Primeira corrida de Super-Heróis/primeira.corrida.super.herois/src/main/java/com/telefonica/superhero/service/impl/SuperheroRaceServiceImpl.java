package com.telefonica.superhero.service.impl;

import com.telefonica.superhero.rest.dto.RaceDataDTO;
import com.telefonica.superhero.rest.dto.SuperheroRaceResponseDTO;
import com.telefonica.superhero.service.ReaderLogService;
import com.telefonica.superhero.service.SuperheroRaceService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of SuperheroRaceService for processing superhero race data.
 */
@Service
public class SuperheroRaceServiceImpl implements SuperheroRaceService {

    private static final String FILE_NAME = "file.txt";

    private static final String SEPARATOR_HYPHEN = "–";

    private ReaderLogService readerLogService;

    /**
     * Constructor injection of ReaderLogServiceImpl.
     *
     * @param readerLogService Instance of ReaderLogServiceImpl to retrieve race data.
     */
    public SuperheroRaceServiceImpl(ReaderLogService readerLogService) {
        this.readerLogService = readerLogService;
    }

    public List<SuperheroRaceResponseDTO> getData() {
        // Created a file in the root because it was requested
        var raceDataList = this.readerLogService.getData(FILE_NAME);

        var groupedBySuperHero = raceDataList.stream()
                .collect(Collectors.groupingBy(RaceDataDTO::getSuperHero));

        RaceDataDTO bestLapRace = raceDataList.stream()
                .min(Comparator.comparing(RaceDataDTO::getLapTime))
                .get();

        var responseDTOS = this.getResponseDTO(groupedBySuperHero, bestLapRace);

        Collections.sort(responseDTOS,
                Comparator.comparingInt(SuperheroRaceResponseDTO::getNumberLapsCompleted).reversed()
        );

        return this.getFinishingPosition(responseDTOS);
    }

    private List<SuperheroRaceResponseDTO> getResponseDTO(Map<String, List<RaceDataDTO>> groupedBySuperHero, RaceDataDTO bestLapRace) {
        Integer backNumber = 0;
        SuperheroRaceResponseDTO dto = new SuperheroRaceResponseDTO();
        List<SuperheroRaceResponseDTO> responseDTOS = new ArrayList<>();
        for (List<RaceDataDTO> list : groupedBySuperHero.values()) {
            for (RaceDataDTO hero : list) {
                dto = new SuperheroRaceResponseDTO();
                if (hero.getSuperHero().contains(SEPARATOR_HYPHEN) && hero.getSuperHero().split(SEPARATOR_HYPHEN).length == 2) {
                    String[] codeName = hero.getSuperHero().split(SEPARATOR_HYPHEN);
                    dto.setSuperHeroCode(Integer.valueOf(codeName[0]));
                    dto.setSuperHeroName(codeName[1]);
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
                    .collect(Collectors.summarizingDouble(RaceDataDTO::getAverageLapSpeed));

            Collections.sort(list,
                    Comparator.comparing(RaceDataDTO::getLapTime)
            );
            dto.setMediaSpeed(list.get(0).getLapTime());
            dto.setBestLapRace(bestLapRace.getLapTime());
            dto.setSuperheroAverageSpeedDuringRace(statistics.getAverage());
            responseDTOS.add(dto);
            backNumber = 0;
        }
        return responseDTOS;
    }

    private List<SuperheroRaceResponseDTO> getFinishingPosition(List<SuperheroRaceResponseDTO> responseDTOS) {
        for (int i = 0; i < responseDTOS.size(); i++) {
            responseDTOS.get(i).setFinishingPosition((i + 1));
        }
        return responseDTOS;
    }

    private Duration getTotalDuration(List<RaceDataDTO> list) {
        return list.stream()
                .filter(r -> r.getLapTime() != null)
                .map(obj -> Duration.between(LocalTime.MIDNIGHT, obj.getLapTime()))
                .reduce(Duration.ZERO, Duration::plus);
    }
}
