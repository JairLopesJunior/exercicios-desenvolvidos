package com.telefonica.bitmap.service;

import com.telefonica.bitmap.rest.dto.ImagemBitmapRequestDTO;
import com.telefonica.bitmap.rest.dto.ImagemBitmapResponseDTO;
import com.telefonica.bitmap.service.impl.ImagemBitmapServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
public class ImagemBitmapServiceImplTest {

    private static final int MAX_VALUE = 15;

    @InjectMocks
    private ImagemBitmapServiceImpl service;

    private List<ImagemBitmapRequestDTO> bitmapList;

    private ImagemBitmapRequestDTO dto;

    private List<Integer> integers;

    private List<ImagemBitmapResponseDTO> responseDTOS;

    private ImagemBitmapResponseDTO responseDTO;

    @BeforeEach()
    void setup() {
        this.bitmapList = new ArrayList<>();
        this.dto = new ImagemBitmapRequestDTO();

        this.responseDTOS = new ArrayList<>();
    }

    @Test
    public void getData_withAnOccurrence_success() {
        this.integers = Arrays.asList(2, 1, 9, 15);
        this.dto.setNumbers(integers);
        this.bitmapList.add(dto);

        this.getResponseDTOs(1);

        List<ImagemBitmapResponseDTO> fountResponseDTOS = this.service.getData(bitmapList);

        assertThat(fountResponseDTOS, notNullValue());
        assertThat(fountResponseDTOS, is(equalTo(this.responseDTOS)));
    }

    @Test
    public void getData_withTwoOccurrence_success() {
        this.integers = Arrays.asList(1, 1, 2, 2, 9, 9, 15, 15);
        this.dto.setNumbers(integers);
        this.bitmapList.add(dto);

        this.getResponseDTOs(2);

        List<ImagemBitmapResponseDTO> fountResponseDTOS = this.service.getData(bitmapList);

        assertThat(fountResponseDTOS, notNullValue());
        assertThat(fountResponseDTOS, is(equalTo(this.responseDTOS)));
    }

    private void getResponseDTOs(Integer numberOccurrence) {
        for (int i = 0; i <= MAX_VALUE; i++) {
            this.responseDTO = new ImagemBitmapResponseDTO();
            this.responseDTO.setEntryNumber(i);
            if (this.integers.contains(i)) {
                this.responseDTO.setNumberOccurrence(numberOccurrence);
            } else {
                this.responseDTO.setNumberOccurrence(0);
            }
            this.responseDTOS.add(this.responseDTO);
        }
    }
}
