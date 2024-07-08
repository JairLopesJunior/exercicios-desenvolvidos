package com.telefonica.superhero.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceData {

    private LocalTime hour;
    private String superHero;
    private Integer backNumber;
    private LocalTime lapTime;
    private Double averageLapSpeed;
}
