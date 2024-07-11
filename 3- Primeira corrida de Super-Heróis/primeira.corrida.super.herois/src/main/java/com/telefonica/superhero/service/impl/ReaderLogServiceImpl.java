package com.telefonica.superhero.service.impl;

import com.telefonica.superhero.domain.enums.RaceDataEnum;
import com.telefonica.superhero.exception.CustomRaceDataException;
import com.telefonica.superhero.exception.GenericApplicationException;
import com.telefonica.superhero.rest.dto.RaceDataDTO;
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

/**
 * Implementation of ReaderLogService for processing race data from a log file.
 */
@Service
public class ReaderLogServiceImpl implements ReaderLogService {

    private static final String DELIMITER_SEMICOLON = ";";

    private static final String DELIMITER_COMMA = ",";

    private static final String DELIMITER_POINT = ".";

    private static final String MESSAGE_ERROR = "Error processing race data file";

    private static final int MAX_ATTRIBUTE_NUMBER = 5;

    public List<RaceDataDTO> getData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linha;

            List<RaceDataDTO> raceDataList = new ArrayList<>();
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(DELIMITER_SEMICOLON);
                if (dados.length < MAX_ATTRIBUTE_NUMBER) {
                    continue;
                }

                RaceDataDTO raceData = new RaceDataDTO();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
                DateFormat dateFormat = new SimpleDateFormat("m:ss.SSS");

                raceData.setHour(LocalTime.parse(dados[RaceDataEnum.HOUR.getKey()], formatter));

                raceData.setSuperHero(Optional.ofNullable(dados[RaceDataEnum.SUPER_HERO.getKey()]).orElse(Strings.EMPTY));

                if (!dados[2].isEmpty()) {
                    raceData.setBackNumber(Integer.valueOf(dados[RaceDataEnum.BACK_NUMBER.getKey()]));
                }

                raceData.setLapTime(dateFormat.parse(dados[RaceDataEnum.LapTime.getKey()]).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime());

                if (!dados[4].isEmpty()) {
                    raceData.setAverageLapSpeed(Double.valueOf(dados[RaceDataEnum.AVERAGE_LAP_SPEED.getKey()].replace(DELIMITER_COMMA, DELIMITER_POINT)));
                }
                raceDataList.add(raceData);
            }
            return raceDataList;
        } catch (IOException | ParseException | NumberFormatException e) {
            throw new CustomRaceDataException(MESSAGE_ERROR, e);
        } catch (Exception ex) {
            throw new GenericApplicationException(MESSAGE_ERROR, ex);
        }
    }
}

