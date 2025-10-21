package com.sd51.fsneakers.features.product.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    String uploadFile(MultipartFile file) throws IOException;

    Map uploadFileImage(MultipartFile file) throws IOException;
}
