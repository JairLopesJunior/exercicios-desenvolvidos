package com.telefonica;

import java.util.*;

public class ImagemBitmap {

    static Scanner scan;

    public static void main(String[] args) {
        scan = new Scanner(System.in);

        List<int[]> bitmapList = new ArrayList<>();
        getData(bitmapList);

        Map<Integer, Integer> countMap = new HashMap<>();
        initializeBitmap(countMap);

        countBitmapOccurrences(countMap, bitmapList);

        StringBuilder output = new StringBuilder();
        showResponse(output, countMap);
    }

    private static void getData(List<int[]> bitmapList) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            if (line.isEmpty()) {
                break;
            }

            String[] values = line.split(" ");
            int size = values.length;
            int[] row = new int[size];
            for (int i = 0; i < size; i++) {
                row[i] = Integer.parseInt(values[i]);
            }
            bitmapList.add(row);
        }
    }

    private static void initializeBitmap(Map<Integer, Integer> countMap) {
        for (int i = 0; i <= 15; i++) {
            countMap.put(i, 0);
        }
    }

    private static void countBitmapOccurrences(Map<Integer, Integer> countMap, List<int[]> bitmapList) {
        for (int i = 0; i < bitmapList.size(); i++) {
            for (int j = 0; j < bitmapList.get(i).length; j++) {
                int value = bitmapList.get(i)[j];
                countMap.put(value, countMap.get(value) + 1);
            }
        }
    }

    private static void showResponse(StringBuilder output, Map<Integer, Integer> countMap) {
        for (int i = 0; i <= 15; i++) {
            output.append(i).append(": ").append(countMap.get(i)).append(", ");
        }

        System.out.println(output.toString());
    }
}


