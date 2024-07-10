package com.telefonica.bitmap.rest.controller;

import com.telefonica.bitmap.rest.dto.ImagemBitmapDTO;
import com.telefonica.bitmap.rest.dto.ResponseDTO;
import com.telefonica.bitmap.service.impl.ImagemBitmapServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling operations related to bitmap images.
 */
@RestController
@RequestMapping("/api/bitmap")
public class ImagemBitmapController {

    private ImagemBitmapServiceImpl service;

    /**
     * Constructor injection for ImagemBitmapServiceImpl dependency.
     * @param service ImagemBitmapServiceImpl instance to handle bitmap operations.
     */
    @Autowired
    public ImagemBitmapController( ImagemBitmapServiceImpl service ) {
        this.service = service;
    }

    /**
     * Endpoint to receive a list of ImagemBitmapDTO objects, validate them, and process them using ImagemBitmapServiceImpl.
     * Returns a list of ResponseDTO objects containing processed results.
     * @param bitmapList List of ImagemBitmapDTO objects representing bitmap data.
     * @return List of ResponseDTO objects containing processing results.
     */
    @PostMapping(produces = "application/json", consumes = "application/json")
    public List<ResponseDTO> getData(@Valid @RequestBody List<ImagemBitmapDTO> bitmapList) {
        return this.service.getData(bitmapList);
    }
}
