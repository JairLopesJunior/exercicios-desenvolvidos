package com.telefonica.superhero.service;

import com.telefonica.superhero.rest.dto.RaceData;

import java.util.List;

public interface ReaderLogService {

    List<RaceData> getData(String fileName);
}
