package com.telefonica.superhero.service;

import com.telefonica.superhero.exception.CustomRaceDataException;
import com.telefonica.superhero.rest.dto.RaceDataDTO;
import com.telefonica.superhero.service.impl.ReaderLogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ReaderLogServiceTest {

    private static final String SUPERMAN = "Superman";

    private static final String FILE_NAME = "fileTest.txt";

    private ReaderLogService service;

    @BeforeEach()
    void setup() {
        this.service = new ReaderLogServiceImpl();
    }

    @Test
    public void getData_success() throws ParseException {
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

        List<RaceDataDTO> result = this.service.getData(FILE_NAME);

        assertThat(result, notNullValue());
        assertThat(result, is(equalTo(raceDataList)));
    }

    @Test
    public void testGetDataThrowsCustomRaceDataException() {
        String fileName = "nonexistent_file.txt";

        assertThrows(CustomRaceDataException.class, () -> this.service.getData(fileName));
    }
}
