package com.telefonica.bitmap.service.impl;

import com.telefonica.bitmap.rest.dto.ImagemBitmapRequestDTO;
import com.telefonica.bitmap.rest.dto.ImagemBitmapResponseDTO;
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

    private static final int MAX_VALUE = 15;

    public List<ImagemBitmapResponseDTO> getData(List<ImagemBitmapRequestDTO> bitmapList) {
        Map<Integer, Integer> countMap = new HashMap<>();
        initializeBitmap(countMap);

        countBitmapOccurrences(countMap, bitmapList);

        return getResponse(countMap);
    }

    private void initializeBitmap(Map<Integer, Integer> countMap) {
        for (int i = 0; i <= MAX_VALUE; i++) {
            countMap.put(i, 0);
        }
    }

    private void countBitmapOccurrences(Map<Integer, Integer> countMap, List<ImagemBitmapRequestDTO> bitmapList) {
        for (ImagemBitmapRequestDTO dto : bitmapList) {
            List<Integer> values = dto.getNumbers();
            for (int value : values) {
                countMap.put(value, countMap.getOrDefault(value, 0) + 1);
            }
        }
    }

    private List<ImagemBitmapResponseDTO> getResponse(Map<Integer, Integer> countMap) {
        List<ImagemBitmapResponseDTO> responseDTOS = new ArrayList<>();
        for (int i = 0; i <= MAX_VALUE; i++) {
            ImagemBitmapResponseDTO dto = new ImagemBitmapResponseDTO();
            dto.setEntryNumber(i);
            dto.setNumberOccurrence(countMap.get(i));
            responseDTOS.add(dto);
        }

        return responseDTOS;
    }
}
