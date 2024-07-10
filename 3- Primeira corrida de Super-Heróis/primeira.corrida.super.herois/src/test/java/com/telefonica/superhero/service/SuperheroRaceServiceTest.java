package com.telefonica.superhero.service;

import com.telefonica.superhero.rest.dto.RaceDataDTO;
import com.telefonica.superhero.rest.dto.SuperheroRaceResponseDTO;
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

    private static final String SUPERMAN = "Superman";

    @InjectMocks
    public SuperheroRaceServiceImpl service;

    @Mock
    private ReaderLogServiceImpl readerLogService;

    @Test
    public void getData() throws ParseException {
        List<RaceDataDTO> raceDataList = new ArrayList<>();
        RaceDataDTO raceData = new RaceDataDTO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        raceData.setHour(LocalTime.parse("23:49:08.277", formatter));
        raceData.setSuperHero(String.format("%d–%s", 38, SUPERMAN));
        raceData.setBackNumber(1);
        DateFormat dateFormat = new SimpleDateFormat("m:ss.SSS");
        raceData.setLapTime(dateFormat.parse("1:02.852").toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime());
        raceData.setAverageLapSpeed(44.275);
        raceDataList.add(raceData);


        List<SuperheroRaceResponseDTO> responseDTOs =  new ArrayList<>();
        SuperheroRaceResponseDTO dto = new SuperheroRaceResponseDTO();
        dto.setFinishingPosition(1);
        dto.setSuperHeroCode(38);
        dto.setSuperHeroName(SUPERMAN);
        dto.setNumberLapsCompleted(1);
        dto.setTotalTestTime(LocalTime.of(0, 1, 2));

        DateTimeFormatter formatterTwo = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        dto.setMediaSpeed(LocalTime.parse("00:01:02.852", formatterTwo));
        dto.setBestLapRace(LocalTime.parse("00:01:02.852", formatterTwo));

        dto.setSuperheroAverageSpeedDuringRace(44.275);
        responseDTOs.add(dto);

        when(this.readerLogService.getData(any())).thenReturn(raceDataList);

        var response = this.service.getData();

        assertThat(response, notNullValue());
        assertThat(response, is(equalTo(responseDTOs)));
    }
}
