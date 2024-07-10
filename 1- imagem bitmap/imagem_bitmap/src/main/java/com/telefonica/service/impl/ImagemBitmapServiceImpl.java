package main.java.com.telefonica.service.impl;

import main.java.com.telefonica.exception.InvalidCharacterException;
import main.java.com.telefonica.exception.InvalidNumberRangeException;
import main.java.com.telefonica.service.ImagemBitmapService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Implementation of ImagemBitmapService for processing bitmap images.
 */
public class ImagemBitmapServiceImpl implements ImagemBitmapService {

    private Scanner scan;

    private static final int MAX_VALUE = 15;

    private static final String DELIMITER_BLANK_SPACE = " ";

    private static final String TWO_COLON_WITH_SPACE = ": ";

    private static final String COMMA_WITH_SPACE = ", ";

    /**
     * Constructor to initialize with a Scanner object for input.
     * @param scan Scanner object to read input data.
     */
    public ImagemBitmapServiceImpl(Scanner scan) {
        this.scan = scan;
    }

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
                throw new InvalidCharacterException(String.format("Invalid line (contains non-numeric characters): %s", line));
            }

            String[] values = line.split(DELIMITER_BLANK_SPACE);
            int size = values.length;
            int[] row = new int[size];
            for (int i = 0; i < size; i++) {
                row[i] = Integer.parseInt(values[i]);

                // Validate numeric range [0, 15]
                if (row[i] < 0 || row[i] > 15) {
                    throw new InvalidNumberRangeException(String.format("Invalid row (contains numbers less than zero or greater than 15): %s", line));
                }
            }
            bitmapList.add(row);
        }
    }

    @Override
    public void initializeBitmap(Map<Integer, Integer> countMap) {
        for (int i = 0; i <= MAX_VALUE; i++) {
            countMap.put(i, 0);
        }
    }

    @Override
    public void countBitmapOccurrences(Map<Integer, Integer> countMap, List<int[]> bitmapList) {
        for (int i = 0; i < bitmapList.size(); i++) {
            for (int j = 0; j < bitmapList.get(i).length; j++) {
                int value = bitmapList.get(i)[j];
                countMap.put(value, countMap.get(value) + 1);
            }
        }
    }

    @Override
    public String showResponse(Map<Integer, Integer> countMap) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i <= MAX_VALUE; i++) {
            output.append(i).append(TWO_COLON_WITH_SPACE).append(countMap.get(i)).append(COMMA_WITH_SPACE);
        }

        return output.toString();
    }
}
