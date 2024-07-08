package com.telefonica.superhero.service;

import com.telefonica.superhero.rest.dto.RaceData;
import com.telefonica.superhero.service.impl.ReaderLogServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class ReaderLogServiceTest {

    private static final Integer ONE = 1;

    private static final Integer THIRTY_EIGHT = 38;

    private static final String SUPERMAN = "Superman";

    private static final String FILE_NAME = "fileTest.txt";

    private static final Double FORTY_FOUR_AND_FOUR_SEVENTY_FIVE = 44.275;

    @InjectMocks
    public ReaderLogServiceImpl service;

    @Test
    public void getData_success() throws ParseException {
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

        List<RaceData> result = this.service.getData(FILE_NAME);

        assertThat(result, notNullValue());
        assertThat(result, is(equalTo(raceDataList)));
    }
}
