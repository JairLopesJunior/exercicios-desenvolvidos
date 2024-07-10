package com.telefonica.bitmap.service.impl;

import com.telefonica.bitmap.rest.dto.ImagemBitmapDTO;
import com.telefonica.bitmap.rest.dto.ResponseDTO;
import com.telefonica.bitmap.service.ImagemBitmapService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of ImagemBitmapService interface for processing bitmap images.
 */
@Service
public class ImagemBitmapServiceImpl implements ImagemBitmapService {

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final int FIFTEEN = 15;

    /**
     * Processes a list of ImagemBitmapDTO objects to generate a list of ResponseDTO objects.
     * @param bitmapList List of ImagemBitmapDTO objects representing bitmap data.
     * @return List of ResponseDTO objects containing processing results.
     */
    public List<ResponseDTO> getData(List<ImagemBitmapDTO> bitmapList) {
        Map<Integer, Integer> countMap = new HashMap<>();
        initializeBitmap(countMap);

        countBitmapOccurrences(countMap, bitmapList);

        return getResponse(countMap);
    }

    /**
     * Initializes a map with integer keys and values set to zero.
     * @param countMap Map to initialize, where keys represent bitmap values and values represent counts.
     */
    private void initializeBitmap(Map<Integer, Integer> countMap) {
        for (int i = ZERO; i <= FIFTEEN; i++) {
            countMap.put(i, ZERO);
        }
    }

    /**
     * Counts occurrences of bitmap values in the provided list of ImagemBitmapDTO objects and updates the count map accordingly.
     * @param countMap Map to update with bitmap value occurrences.
     * @param bitmapList List of ImagemBitmapDTO objects representing bitmap data.
     */
    private void countBitmapOccurrences(Map<Integer, Integer> countMap, List<ImagemBitmapDTO> bitmapList) {
        for (ImagemBitmapDTO dto : bitmapList) {
            List<Integer> values = dto.getNumbers();
            for (int value : values) {
                countMap.put(value, countMap.getOrDefault(value, ZERO) + ONE);
            }
        }
    }

    /**
     * Generates a list of ResponseDTO objects based on the provided count map.
     * @param countMap Map containing bitmap values as keys and their occurrences as values.
     * @return List of ResponseDTO objects containing bitmap value and occurrence data.
     */
    private List<ResponseDTO> getResponse(Map<Integer, Integer> countMap) {
        List<ResponseDTO> responseDTOS = new ArrayList<>();
        for (int i = ZERO; i <= FIFTEEN; i++) {
            ResponseDTO dto = new ResponseDTO();
            dto.setEntryNumber(i);
            dto.setNumberOccurrence(countMap.get(i));
            responseDTOS.add(dto);
        }

        return responseDTOS;
    }
}
