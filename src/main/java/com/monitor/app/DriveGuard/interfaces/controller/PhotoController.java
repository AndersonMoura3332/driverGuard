package com.monitor.app.DriveGuard.interfaces.controller;

import com.monitor.app.DriveGuard.application.service.PhotoService;
import com.monitor.app.DriveGuard.domain.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @PostMapping("/photo")
    public ResponseEntity<Photo> uploadPhoto(@RequestBody PhotoUploadRequest request) throws IOException {
        byte[] fileData = Base64.getDecoder().decode(request.imageBase64());
        Photo photo = photoService.savePhoto(request.tripId(), fileData);
        return ResponseEntity.ok(photo);
    }


    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPhotoById(@PathVariable String id) throws IOException {
        Photo photo = photoService.getPhotoById(id);
        if (photo == null) {
            return ResponseEntity.notFound().build();
        }
        byte[] photoData = photo.getData(); // Já está em byte[], pois você decodificou o base64 no upload
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return ResponseEntity.ok()
                .headers(headers)
                .body(photoData);
    }
    public record PhotoUploadRequest(
         String tripId,
         String imageBase64
){}
        // Getters e Setters


}
