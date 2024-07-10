package com.telefonica.service.impl;

import com.telefonica.service.ImagemBitmapService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Implementation of ImagemBitmapService for processing bitmap images.
 */
public class ImagemBitmapServiceImpl implements ImagemBitmapService {

    private Scanner scan;

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final int FIFTEEN = 15;

    private static final String blankSpace = " ";

    private static final String twoColonWithSpace = ": ";

    private static final String commaWithSpace = ", ";

    /**
     * Constructor to initialize with a Scanner object for input.
     * @param scan Scanner object to read input data.
     */
    public ImagemBitmapServiceImpl(Scanner scan) {
        this.scan = scan;
    }

    /**
     * Retrieves bitmap data from input and populates the provided list.
     * Validates input lines to ensure they contain only numeric values within range [0, 15].
     * Exits program if invalid input is encountered.
     * @param bitmapList List to store bitmap data, where each element is an array of integers representing a bitmap row.
     */
    @Override
    public void getData(List<int[]> bitmapList) {
        System.out.println("Please enter a sequence of numbers separated by spaces and press ENTER twice to see the result:");

        while (this.scan.hasNextLine()) {
            String line = this.scan.nextLine();

            if (line.isEmpty()) {
                break;
            }

            // Validate input format (numeric values separated by spaces)
            if (!line.matches("^(\\d+\\s*)+$")) {
                System.err.println("Linha inválida (contém caracteres não numéricos): " + line);
                System.exit(0);
            }

            String[] values = line.split(blankSpace);
            int size = values.length;
            int[] row = new int[size];
            for (int i = ZERO; i < size; i++) {
                row[i] = Integer.parseInt(values[i]);

                // Validate numeric range [0, 15]
                if (row[i] < 0 || row[i] > 15) {
                    System.err.println("Linha inválida (contém números menores que zero ou maior que 15): " + line);
                    System.exit(0);
                }
            }
            bitmapList.add(row);
        }
    }

    /**
     * Initializes a map with integer keys [0-15] and values set to zero.
     * @param countMap Map to initialize, where keys represent bitmap values and values represent counts.
     */
    @Override
    public void initializeBitmap(Map<Integer, Integer> countMap) {
        for (int i = ZERO; i <= FIFTEEN; i++) {
            countMap.put(i, ZERO);
        }
    }

    /**
     * Counts occurrences of bitmap values in the provided list of bitmaps and updates the count map accordingly.
     * @param countMap Map to update with bitmap value occurrences.
     * @param bitmapList List of bitmaps to process, where each element is an array of integers representing a bitmap row.
     */
    @Override
    public void countBitmapOccurrences(Map<Integer, Integer> countMap, List<int[]> bitmapList) {
        for (int i = ZERO; i < bitmapList.size(); i++) {
            for (int j = ZERO; j < bitmapList.get(i).length; j++) {
                int value = bitmapList.get(i)[j];
                countMap.put(value, countMap.get(value) + ONE);
            }
        }
    }

    /**
     * Constructs a response string based on the count map.
     * @param countMap Map containing counts of bitmap values.
     * @return String representation of the count map, formatted as "value: count, value: count, ...".
     */
    @Override
    public String showResponse(Map<Integer, Integer> countMap) {
        StringBuilder output = new StringBuilder();

        for (int i = ZERO; i <= FIFTEEN; i++) {
            output.append(i).append(twoColonWithSpace).append(countMap.get(i)).append(commaWithSpace);
        }

        return output.toString();
    }
}
