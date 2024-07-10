package com.telefonica.superhero.service;

import com.telefonica.superhero.rest.dto.SuperheroRaceResponseDTO;

import java.util.List;

/**
 * Service interface for handling superhero race data.
 */
public interface SuperheroRaceService {

    /**
     * Retrieves superhero race data.
     *
     * @return List of ResponseDTO objects containing superhero race information.
     */
    List<SuperheroRaceResponseDTO> getData();
}
