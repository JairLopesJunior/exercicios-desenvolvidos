package com.telefonica.bitmap.rest.dto;

import com.telefonica.bitmap.validation.ValidRange;
import lombok.Data;

import java.util.List;

@Data
public class ImagemBitmapDTO {

    @ValidRange
    private List<Integer> numbers;
}
