package com.telefonica.bitmap.service;

import com.telefonica.bitmap.rest.dto.ImagemBitmapDTO;
import com.telefonica.bitmap.rest.dto.ResponseDTO;
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

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final int TWO = 2;

    private static final int NINE = 9;

    private static final int FIFTEEN = 15;

    @InjectMocks
    private ImagemBitmapServiceImpl service;

    private List<ImagemBitmapDTO> bitmapList;

    private ImagemBitmapDTO dto;

    private List<Integer> integers;

    private List<ResponseDTO> responseDTOS;

    private ResponseDTO responseDTO;

    @BeforeEach()
    void setup() {
        this.bitmapList = new ArrayList<>();
        this.dto = new ImagemBitmapDTO();
        this.integers = Arrays.asList(TWO, ONE, NINE, FIFTEEN);

        this.responseDTOS = new ArrayList<>();
    }

    @Test
    public void getData_withAnOccurrence_success() {
        this.dto.setNumbers(integers);
        this.bitmapList.add(dto);

        this.getResponseDTOs(ONE);

        List<ResponseDTO> fountResponseDTOS = this.service.getData(bitmapList);

        assertThat(fountResponseDTOS, notNullValue());
        assertThat(fountResponseDTOS, is(equalTo(this.responseDTOS)));
    }

    @Test
    public void getData_withTwoOccurrence_success() {
        this.integers = Arrays.asList(ONE, ONE, TWO, TWO, NINE, NINE, FIFTEEN, FIFTEEN);
        this.dto.setNumbers(integers);
        this.bitmapList.add(dto);

        this.getResponseDTOs(TWO);

        List<ResponseDTO> fountResponseDTOS = this.service.getData(bitmapList);

        assertThat(fountResponseDTOS, notNullValue());
        assertThat(fountResponseDTOS, is(equalTo(this.responseDTOS)));
    }

    private void getResponseDTOs(Integer numberOccurrence) {
        for (int i = ZERO; i <= FIFTEEN; i++) {
            this.responseDTO = new ResponseDTO();
            this.responseDTO.setEntryNumber(i);
            if (this.integers.contains(i)) {
                this.responseDTO.setNumberOccurrence(numberOccurrence);
            } else {
                this.responseDTO.setNumberOccurrence(ZERO);
            }
            this.responseDTOS.add(this.responseDTO);
        }
    }
}
