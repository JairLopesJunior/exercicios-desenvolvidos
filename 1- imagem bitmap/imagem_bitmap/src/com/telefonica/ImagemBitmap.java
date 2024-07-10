package com.telefonica;

import com.telefonica.service.impl.ImagemBitmapServiceImpl;

import java.util.*;

/**
 * Main class to process bitmap images.
 */
public class ImagemBitmap {

    static Scanner scan;

    /**
     * Main method that starts the application.
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        scan = new Scanner(System.in);

        ImagemBitmapServiceImpl service = new ImagemBitmapServiceImpl(scan);

        List<int[]> bitmapList = new ArrayList<>();
        service.getData(bitmapList);

        Map<Integer, Integer> countMap = new HashMap<>();
        service.initializeBitmap(countMap);

        service.countBitmapOccurrences(countMap, bitmapList);

        String response = service.showResponse(countMap);

        System.out.println(response);
    }
}


