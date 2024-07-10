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

/**
 * Implementation of SuperheroRaceService for processing superhero race data.
 */
@Service
public class SuperheroRaceServiceImpl implements SuperheroRaceService {

    private static final Integer ZERO = 0;

    private static final Integer ONE = 1;

    private static final Integer TWO = 2;

    private static final String FILE_NAME = "file.txt";

    private static final String HYPHEN = "–";

    private ReaderLogServiceImpl readerLogService;

    /**
     * Constructor injection of ReaderLogServiceImpl.
     *
     * @param readerLogService Instance of ReaderLogServiceImpl to retrieve race data.
     */
    @Autowired
    public SuperheroRaceServiceImpl(ReaderLogServiceImpl readerLogService) {
        this.readerLogService = readerLogService;
    }

    /**
     * Retrieves processed race data and calculates race statistics.
     *
     * @return A sorted list of ResponseDTO objects containing race statistics.
     */
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

    /**
     * Creates ResponseDTO objects from grouped race data and calculates statistics.
     *
     * @param groupedBySuperHero Map grouping RaceData by superhero.
     * @param bestLapRace        RaceData object representing the superhero with the best lap time.
     * @return List of ResponseDTO objects containing race statistics.
     */
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

    /**
     * Sets finishing positions based on the order of ResponseDTO objects.
     *
     * @param responseDTOS List of ResponseDTO objects to set finishing positions.
     * @return Updated list of ResponseDTO objects with finishing positions.
     */
    private List<ResponseDTO> getFinishingPosition(List<ResponseDTO> responseDTOS) {
        for (int i = ZERO; i < responseDTOS.size(); i++) {
            responseDTOS.get(i).setFinishingPosition((i + ONE));
        }
        return responseDTOS;
    }

    /**
     * Calculates the total duration of all laps in a race.
     *
     * @param list List of RaceData objects representing laps of a race.
     * @return Total duration of all laps.
     */
    private Duration getTotalDuration(List<RaceData> list) {
        return list.stream()
                .filter(r -> r.getLapTime() != null)
                .map(obj -> Duration.between(LocalTime.MIDNIGHT, obj.getLapTime()))
                .reduce(Duration.ZERO, Duration::plus);
    }
}
