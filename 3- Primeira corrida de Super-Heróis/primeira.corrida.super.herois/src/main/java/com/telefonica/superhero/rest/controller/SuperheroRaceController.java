package com.telefonica.superhero.rest.controller;

import com.telefonica.superhero.rest.dto.ResponseDTO;
import com.telefonica.superhero.service.SuperheroRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/superheroes")
public class SuperheroRaceController {

    private SuperheroRaceService service;

    @Autowired
    public SuperheroRaceController(SuperheroRaceService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResponseDTO> getData() {
        return this.service.getData();
    }
}
