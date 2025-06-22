package com.api.ecommerce.upload.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadService {
    @Value("${IMG_API_KEY}")
    private String API_KEY;
    @Value("${IMG_EXPIRATION}")
    private int EXPIRATION;

    public List<String> uploadImages(List<MultipartFile> files) {
        return files.stream()
                .map(this::uploadSingleImage)
                .toList();
    }

    private String uploadSingleImage(MultipartFile file) {
        try {
            byte[] imageBytes = file.getBytes();
            String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("key", API_KEY);
            body.add("image", encodedImage);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

            String url = "https://api.imgbb.com/1/upload?expiration=" + EXPIRATION;

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String responseBody = response.getBody();
                int start = responseBody.indexOf("\"url\":\"") + 7;
                int end = responseBody.indexOf("\"", start);
                return responseBody.substring(start, end).replace("\\/", "/");
            } else {
                throw new RuntimeException("Error al subir la imagen: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Fallo al procesar la imagen: " + e.getMessage(), e);
        }
    }
}
