package com.telefonica.bitmap.service;

import com.telefonica.bitmap.rest.dto.ImagemBitmapRequestDTO;
import com.telefonica.bitmap.rest.dto.ImagemBitmapResponseDTO;

import java.util.List;

/**
 * Service interface for processing bitmap images.
 */
public interface ImagemBitmapService {

    /**
     * Processes a list of ImagemBitmapDTO objects to generate a list of ResponseDTO objects.
     * @param bitmapList List of ImagemBitmapDTO objects representing bitmap data.
     * @return List of ResponseDTO objects containing processing results.
     */
    List<ImagemBitmapResponseDTO> getData(List<ImagemBitmapRequestDTO> bitmapList);
}
