package com.telefonica.bitmap.service.impl;

import com.telefonica.bitmap.rest.dto.ImagemBitmapDTO;
import com.telefonica.bitmap.rest.dto.ResponseDTO;
import com.telefonica.bitmap.service.ImagemBitmapService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImagemBitmapServiceImpl implements ImagemBitmapService {

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final int FIFTEEN = 15;

    public List<ResponseDTO> getData(List<ImagemBitmapDTO> bitmapList) {
        Map<Integer, Integer> countMap = new HashMap<>();
        initializeBitmap(countMap);

        countBitmapOccurrences(countMap, bitmapList);

        return getResponse(countMap);
    }

    private void initializeBitmap(Map<Integer, Integer> countMap) {
        for (int i = ZERO; i <= FIFTEEN; i++) {
            countMap.put(i, ZERO);
        }
    }

    private void countBitmapOccurrences(Map<Integer, Integer> countMap, List<ImagemBitmapDTO> bitmapList) {
        for (ImagemBitmapDTO dto : bitmapList) {
            List<Integer> values = dto.getNumbers();
            for (int value : values) {
                countMap.put(value, countMap.getOrDefault(value, ZERO) + ONE);
            }
        }
    }

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
