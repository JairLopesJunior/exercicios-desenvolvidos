package com.telefonica.bitmap.rest.dto;

import lombok.Data;

/**
 * Data transfer object (DTO) for representing response data.
 */
@Data
public class ResponseDTO {

    /**
     * Entry number corresponding to bitmap value.
     */
    private Integer entryNumber;

    /**
     * Number of occurrences of the bitmap value.
     */
    private Integer numberOccurrence;
}
