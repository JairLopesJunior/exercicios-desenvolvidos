package com.telefonica.bitmap.rest.controller;

import com.telefonica.bitmap.rest.dto.ImagemBitmapRequestDTO;
import com.telefonica.bitmap.rest.dto.ImagemBitmapResponseDTO;
import com.telefonica.bitmap.service.ImagemBitmapService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling operations related to bitmap images.
 */
@RestController
@RequestMapping("/api/bitmap")
public class ImagemBitmapController {

    private ImagemBitmapService service;

    /**
     * Constructor injection for ImagemBitmapServiceImpl dependency.
     * @param service ImagemBitmapServiceImpl instance to handle bitmap operations.
     */
    public ImagemBitmapController( ImagemBitmapService service ) {
        this.service = service;
    }

    /**
     * Endpoint to receive a list of ImagemBitmapDTO objects, validate them, and process them using ImagemBitmapServiceImpl.
     * Returns a list of ResponseDTO objects containing processed results.
     * @param bitmapList List of ImagemBitmapDTO objects representing bitmap data.
     * @return List of ResponseDTO objects containing processing results.
     */
    @PostMapping(produces = "application/json", consumes = "application/json")
    public List<ImagemBitmapResponseDTO> getData(@Valid @RequestBody List<ImagemBitmapRequestDTO> bitmapList) {
        return this.service.getData(bitmapList);
    }
}
