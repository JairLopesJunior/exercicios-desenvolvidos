package com.telefonica.superhero.rest.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ResponseDTO {

    private Integer finishingPosition;
    private Integer superHeroCode;
    private String superHeroName;
    private Integer numberLapsCompleted;
    private LocalTime totalTestTime;
    private LocalTime mediaSpeed;
    private LocalTime bestLapRace;
    private Double superheroAverageSpeedDuringRace;
}
