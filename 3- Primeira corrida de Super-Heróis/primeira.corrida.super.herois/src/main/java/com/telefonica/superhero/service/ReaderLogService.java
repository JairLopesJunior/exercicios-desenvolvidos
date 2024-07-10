package com.telefonica.superhero.service;

import com.telefonica.superhero.rest.dto.RaceData;

import java.util.List;

/**
 * Service interface for reading race log data.
 */
public interface ReaderLogService {

    /**
     * Retrieves race data from a specified file.
     *
     * @param fileName Name of the file containing race data.
     * @return List of RaceData objects parsed from the file.
     */
    List<RaceData> getData(String fileName);
}
