package com.telefonica.bitmap.rest.controller;

import com.telefonica.bitmap.rest.dto.ImagemBitmapDTO;
import com.telefonica.bitmap.rest.dto.ResponseDTO;
import com.telefonica.bitmap.service.impl.ImagemBitmapServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bitmap")
public class ImagemBitmapController {

    private ImagemBitmapServiceImpl service;

    @Autowired
    public ImagemBitmapController( ImagemBitmapServiceImpl service ) {
        this.service = service;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public List<ResponseDTO> getData(@Valid @RequestBody List<ImagemBitmapDTO> bitmapList) {
        return this.service.getData(bitmapList);
    }
}
