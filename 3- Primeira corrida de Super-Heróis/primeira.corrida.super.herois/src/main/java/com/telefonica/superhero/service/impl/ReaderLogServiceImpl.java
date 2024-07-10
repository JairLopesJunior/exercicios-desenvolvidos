package com.telefonica.superhero.service.impl;

import com.telefonica.superhero.exception.CustomRaceDataException;
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

    public List<RaceDataDTO> getData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linha;

            List<RaceDataDTO> raceDataList = new ArrayList<>();
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(DELIMITER_SEMICOLON);
                if (dados.length < 5) {
                    continue;
                }

                RaceDataDTO raceData = new RaceDataDTO();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
                DateFormat dateFormat = new SimpleDateFormat("m:ss.SSS");

                raceData.setHour(LocalTime.parse(dados[0], formatter));

                raceData.setSuperHero(Optional.ofNullable(dados[1]).orElse(Strings.EMPTY));

                if (!dados[2].isEmpty()) {
                    raceData.setBackNumber(Integer.valueOf(dados[2]));
                }

                raceData.setLapTime(dateFormat.parse(dados[3]).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime());

                if (!dados[4].isEmpty()) {
                    raceData.setAverageLapSpeed(Double.valueOf(dados[4].replace(DELIMITER_COMMA, DELIMITER_POINT)));
                }
                raceDataList.add(raceData);
            }
            return raceDataList;
        } catch (IOException | ParseException | NumberFormatException e) {
            throw new CustomRaceDataException(MESSAGE_ERROR, e);
        } catch (Exception ex) {
            throw new CustomRaceDataException(MESSAGE_ERROR, ex);
        }
    }
}

