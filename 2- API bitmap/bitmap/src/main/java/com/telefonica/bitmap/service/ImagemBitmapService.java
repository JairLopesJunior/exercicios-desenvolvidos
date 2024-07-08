package com.telefonica.bitmap.service;

import com.telefonica.bitmap.rest.dto.ImagemBitmapDTO;
import com.telefonica.bitmap.rest.dto.ResponseDTO;

import java.util.List;

public interface ImagemBitmapService {

    List<ResponseDTO> getData(List<ImagemBitmapDTO> bitmapList);
}
