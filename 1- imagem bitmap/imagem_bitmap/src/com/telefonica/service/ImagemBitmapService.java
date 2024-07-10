package com.telefonica.service;

import java.util.List;
import java.util.Map;

/**
 * Interface defining operations for processing bitmap images.
 */
public interface ImagemBitmapService {

    /**
     * Retrieves bitmap data from input and populates the provided list.
     * @param bitmapList List to store bitmap data, where each element is an array of integers representing a bitmap row.
     */
    void getData(List<int[]> bitmapList);

    /**
     * Initializes a map with integer keys and values set to zero.
     * @param countMap Map to initialize, where keys represent bitmap values and values represent counts.
     */
    void initializeBitmap(Map<Integer, Integer> countMap);

    /**
     * Counts occurrences of bitmap values in the provided list of bitmaps and updates the count map accordingly.
     * @param countMap Map to update with bitmap value occurrences.
     * @param bitmapList List of bitmaps to process, where each element is an array of integers representing a bitmap row.
     */
    void countBitmapOccurrences(Map<Integer, Integer> countMap, List<int[]> bitmapList);

    /**
     * Constructs a response string based on the count map.
     * @param countMap Map containing counts of bitmap values.
     * @return Response string representing the count of bitmap values.
     */
    String showResponse(Map<Integer, Integer> countMap);
}
