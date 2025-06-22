package com.api.ecommerce.upload.controller;

import com.api.ecommerce.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {
    private final UploadService uploadService;

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> uploadImages(@RequestParam("file") List<MultipartFile> files) {
        return ResponseEntity.ok(uploadService.uploadImages(files));
    }
}