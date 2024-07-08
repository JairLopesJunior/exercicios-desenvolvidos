package com.telefonica.superhero.service;

import com.telefonica.superhero.exception.CustomRaceDataException;
import com.telefonica.superhero.rest.dto.RaceData;
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
public class ReaderLogService {

    public List<RaceData> getData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linha;

            List<RaceData> raceDataList = new ArrayList<>();
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length < 5) {
                    continue;
                }

                RaceData raceData = new RaceData();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
                DateFormat dateFormat = new SimpleDateFormat("m:ss.SSS");

                raceData.setHour(LocalTime.parse(dados[0], formatter));

                raceData.setSuperHero(Optional.ofNullable(dados[1]).orElse(""));

                if (!dados[2].isEmpty()) {
                    raceData.setBackNumber(Integer.valueOf(dados[2]));
                }

                raceData.setLapTime(dateFormat.parse(dados[3]).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime());

                if (!dados[4].isEmpty()) {
                    raceData.setAverageLapSpeed(Double.valueOf(dados[4].replace(",", ".")));
                }
                raceDataList.add(raceData);
            }
            return raceDataList;
        } catch (IOException | ParseException | NumberFormatException e) {
            throw new CustomRaceDataException("Error processing race data file", e);
        }
    }
}

