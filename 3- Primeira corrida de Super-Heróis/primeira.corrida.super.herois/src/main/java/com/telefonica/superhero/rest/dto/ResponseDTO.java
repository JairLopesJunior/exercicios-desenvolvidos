package com.telefonica.superhero.rest.dto;

import lombok.Data;

import java.time.LocalTime;

/**
 * Data Transfer Object (DTO) representing response data related to a race event.
 */
@Data
public class ResponseDTO {

    /**
     * Finishing position of the superhero in the race.
     */
    private Integer finishingPosition;

    /**
     * Code identifying the superhero.
     */
    private Integer superHeroCode;

    /**
     * Name of the superhero.
     */
    private String superHeroName;

    /**
     * Number of laps completed by the superhero in the race.
     */
    private Integer numberLapsCompleted;

    /**
     * Total test time of the superhero during the race.
     */
    private LocalTime totalTestTime;

    /**
     * Average speed of the superhero during the race.
     */
    private LocalTime mediaSpeed;

    /**
     * Best lap time achieved by the superhero during the race.
     */
    private LocalTime bestLapRace;

    /**
     * Average speed of the superhero during the entire race.
     */
    private Double superheroAverageSpeedDuringRace;
}
