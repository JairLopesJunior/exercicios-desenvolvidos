package com.telefonica.superhero.service;

import com.telefonica.superhero.rest.dto.RaceData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileReader;
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

    @InjectMocks
    public ReaderLogService service;

    @Mock
    private FileReader fileReader;

    @Test
    public void getData_success() throws ParseException {
        List<RaceData> raceDataList = new ArrayList<>();
        RaceData raceData = new RaceData();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        raceData.setHour(LocalTime.parse("23:49:08.277", formatter));
        raceData.setSuperHero("038–Superman");
        raceData.setBackNumber(1);
        DateFormat dateFormat = new SimpleDateFormat("m:ss.SSS");
        raceData.setLapTime(dateFormat.parse("1:02.852").toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime());
        raceData.setAverageLapSpeed(44.275);
        raceDataList.add(raceData);

        List<RaceData> result = this.service.getData("fileTest.txt");

        assertThat(result, notNullValue());
        assertThat(result, is(equalTo(raceDataList)));
    }
}
