package com.telefonica.bitmap.rest.dto;

import com.telefonica.bitmap.validation.ValidRange;
import lombok.Data;

import java.util.List;

/**
 * Data transfer object (DTO) for representing bitmap data.
 */
@Data
public class ImagemBitmapRequestDTO {

    /**
     * List of integers representing bitmap numbers.
     */
    @ValidRange
    private List<Integer> numbers;
}
