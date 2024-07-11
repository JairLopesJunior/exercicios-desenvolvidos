package test.com.telefonica.service.impl;

import main.java.com.telefonica.exception.InvalidCharacterException;
import main.java.com.telefonica.exception.InvalidNumberRangeException;
import main.java.com.telefonica.service.impl.ImagemBitmapServiceImpl;
import main.java.com.telefonica.service.ImagemBitmapService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ImagemBitmapServiceImplTest {

    @Test
    public void testGetDataValidInput() {
        String input = "1 2 3\n\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ImagemBitmapService imagemBitmapService = new ImagemBitmapServiceImpl(scanner);

        List<int[]> bitmapList = new ArrayList<>();
        imagemBitmapService.getData(bitmapList);

        assertEquals(1, bitmapList.size());
        assertArrayEquals(new int[]{1, 2, 3}, bitmapList.get(0));
    }

    @Test
    public void testGetDataInvalidInputNonNumeric() {
        String input = "1 2 a\n\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ImagemBitmapService imagemBitmapService = new ImagemBitmapServiceImpl(scanner);

        List<int[]> bitmapList = new ArrayList<>();
        assertThrows(InvalidCharacterException.class, () -> {
            imagemBitmapService.getData(bitmapList);
        });
    }

    @Test
    public void testGetDataInvalidInputOutOfRange() {
        String input = "1 2 16\n\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ImagemBitmapService imagemBitmapService = new ImagemBitmapServiceImpl(scanner);

        List<int[]> bitmapList = new ArrayList<>();
        assertThrows(InvalidNumberRangeException.class, () -> {
            imagemBitmapService.getData(bitmapList);
        });
    }

    @Test
    public void testInitializeBitmap() {
        String input = "1 2 1\n\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ImagemBitmapService imagemBitmapService = new ImagemBitmapServiceImpl(scanner);

        Map<Integer, Integer> countMap = new HashMap<>();
        imagemBitmapService.initializeBitmap(countMap);

        for (int i = 0; i <= ImagemBitmapServiceImpl.BITMAP_MAX_VALUE; i++) {
            assertEquals(0, countMap.get(i));
        }
    }

    @Test
    public void testCountBitmapOccurrences() {
        String input = "";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ImagemBitmapService imagemBitmapService = new ImagemBitmapServiceImpl(scanner);

        Map<Integer, Integer> countMap = new HashMap<>();
        imagemBitmapService.initializeBitmap(countMap);

        List<int[]> bitmapList = new ArrayList<>();
        bitmapList.add(new int[]{1, 2, 3});
        bitmapList.add(new int[]{1, 2, 3, 3});

        imagemBitmapService.countBitmapOccurrences(countMap, bitmapList);

        assertEquals(0, countMap.get(0));
        assertEquals(2, countMap.get(1));
        assertEquals(2, countMap.get(2));
        assertEquals(3, countMap.get(3));
    }

    @Test
    public void testShowResponse() {
        String input = "";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ImagemBitmapService imagemBitmapService = new ImagemBitmapServiceImpl(scanner);
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i <= ImagemBitmapServiceImpl.BITMAP_MAX_VALUE; i++) {
            countMap.put(i, i);
        }

        String expectedResponse = "0: 0, 1: 1, 2: 2, 3: 3, 4: 4, 5: 5, 6: 6, 7: 7, 8: 8, 9: 9, 10: 10, 11: 11, 12: 12, 13: 13, 14: 14, 15: 15";

        String response = imagemBitmapService.showResponse(countMap);

        assertEquals(expectedResponse, response);
    }
}