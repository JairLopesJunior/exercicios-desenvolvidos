package com.telefonica.superhero.rest.controller;

import com.telefonica.superhero.rest.dto.ResponseDTO;
import com.telefonica.superhero.service.impl.SuperheroRaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for managing superhero race data.
 * Handles endpoints related to superhero race information.
 */
@RestController
@RequestMapping("/api/superheroes")
public class SuperheroRaceController {

    private SuperheroRaceServiceImpl service;

    /**
     * Constructor for SuperheroRaceController.
     * @param service The superhero race service to inject.
     */
    @Autowired
    public SuperheroRaceController(SuperheroRaceServiceImpl service) {
        this.service = service;
    }

    /**
     * Retrieves race data for superheroes.
     * @return List of ResponseDTO containing race data.
     */
    @GetMapping
    public List<ResponseDTO> getData() {
        return this.service.getData();
    }
}
