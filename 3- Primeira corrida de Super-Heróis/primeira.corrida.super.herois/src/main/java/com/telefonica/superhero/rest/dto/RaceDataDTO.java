package com.telefonica.superhero.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * Represents data related to a race event.
 * Contains information such as race time, superhero name, lap details, and average lap speed.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceDataDTO {

    /**
     * Time of the race event.
     */
    private LocalTime hour;

    /**
     * Name of the superhero participating in the race.
     */
    private String superHero;

    /**
     * Back number assigned to the superhero.
     */
    private Integer backNumber;

    /**
     * Lap time of the superhero during the race.
     */
    private LocalTime lapTime;

    /**
     * Average lap speed of the superhero during the race.
     */
    private Double averageLapSpeed;
}
