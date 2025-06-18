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

@RestController
@RequestMapping("photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @PostMapping
    public ResponseEntity<Photo> uploadPhoto(@RequestParam String tripId, @RequestParam("file") MultipartFile file) throws IOException {
        byte[] fileData = file.getBytes();
        Photo photo = photoService.savePhoto(tripId, fileData);
        return ResponseEntity.ok(photo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPhotoById(@PathVariable String id) throws IOException {
        Photo photo = photoService.getPhotoById(id);
        if (photo == null) {
            return ResponseEntity.notFound().build();
        }
        byte[] photoData = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return ResponseEntity.ok()
                .headers(headers)
                .body(photoData);
    }
}
