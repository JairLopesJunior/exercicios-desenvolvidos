package com.telefonica.superhero.service.impl;

import com.telefonica.superhero.exception.CustomRaceDataException;
import com.telefonica.superhero.rest.dto.RaceData;
import com.telefonica.superhero.service.ReaderLogService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReaderLogServiceImpl implements ReaderLogService {

    private static final Integer ZERO = 0;

    private static final Integer ONE = 1;

    private static final Integer TWO = 2;

    private static final Integer THREE = 3;

    private static final Integer FOUR = 4;

    private static final Integer FIVE = 5;

    private static final String SEMICOLON = ";";

    private static final String COMMA = ",";

    private static final String POINT = ".";

    private static final String MESSAGE_ERROR = "Error processing race data file";

    public List<RaceData> getData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linha;

            List<RaceData> raceDataList = new ArrayList<>();
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(SEMICOLON);
                if (dados.length < FIVE) {
                    continue;
                }

                RaceData raceData = new RaceData();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
                DateFormat dateFormat = new SimpleDateFormat("m:ss.SSS");

                raceData.setHour(LocalTime.parse(dados[ZERO], formatter));

                raceData.setSuperHero(Optional.ofNullable(dados[ONE]).orElse(Strings.EMPTY));

                if (!dados[TWO].isEmpty()) {
                    raceData.setBackNumber(Integer.valueOf(dados[TWO]));
                }

                raceData.setLapTime(dateFormat.parse(dados[THREE]).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime());

                if (!dados[FOUR].isEmpty()) {
                    raceData.setAverageLapSpeed(Double.valueOf(dados[FOUR].replace(COMMA, POINT)));
                }
                raceDataList.add(raceData);
            }
            return raceDataList;
        } catch (IOException | ParseException | NumberFormatException e) {
            throw new CustomRaceDataException(MESSAGE_ERROR, e);
        }
    }
}

