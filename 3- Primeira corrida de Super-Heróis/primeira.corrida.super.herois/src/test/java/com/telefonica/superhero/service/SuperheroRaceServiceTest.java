package com.telefonica.superhero.service;

import com.telefonica.superhero.rest.dto.RaceData;
import com.telefonica.superhero.rest.dto.ResponseDTO;
import com.telefonica.superhero.service.impl.ReaderLogServiceImpl;
import com.telefonica.superhero.service.impl.SuperheroRaceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class SuperheroRaceServiceTest {

    private static final Integer ZERO = 0;

    private static final Integer ONE = 1;

    private static final Integer TWO = 2;

    private static final Integer THIRTY_EIGHT = 38;

    private static final Double FORTY_FOUR_AND_FOUR_SEVENTY_FIVE = 44.275;

    private static final String SUPERMAN = "Superman";

    @InjectMocks
    public SuperheroRaceServiceImpl service;

    @Mock
    private ReaderLogServiceImpl readerLogService;

    @Test
    public void getData() throws ParseException {
        List<RaceData> raceDataList = new ArrayList<>();
        RaceData raceData = new RaceData();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        raceData.setHour(LocalTime.parse("23:49:08.277", formatter));
        raceData.setSuperHero(String.format("%d–%s", THIRTY_EIGHT, SUPERMAN));
        raceData.setBackNumber(ONE);
        DateFormat dateFormat = new SimpleDateFormat("m:ss.SSS");
        raceData.setLapTime(dateFormat.parse("1:02.852").toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime());
        raceData.setAverageLapSpeed(FORTY_FOUR_AND_FOUR_SEVENTY_FIVE);
        raceDataList.add(raceData);


        List<ResponseDTO> responseDTOs =  new ArrayList<>();
        ResponseDTO dto = new ResponseDTO();
        dto.setFinishingPosition(1);
        dto.setSuperHeroCode(THIRTY_EIGHT);
        dto.setSuperHeroName(SUPERMAN);
        dto.setNumberLapsCompleted(1);
        dto.setTotalTestTime(LocalTime.of(ZERO, ONE, TWO));

        DateTimeFormatter formatterTwo = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        dto.setMediaSpeed(LocalTime.parse("00:01:02.852", formatterTwo));
        dto.setBestLapRace(LocalTime.parse("00:01:02.852", formatterTwo));

        dto.setSuperheroAverageSpeedDuringRace(FORTY_FOUR_AND_FOUR_SEVENTY_FIVE);
        responseDTOs.add(dto);

        when(this.readerLogService.getData(any())).thenReturn(raceDataList);

        var response = this.service.getData();

        assertThat(response, notNullValue());
        assertThat(response, is(equalTo(responseDTOs)));
    }
}
