package com.monitor.app.DriveGuard.application.service;


import com.monitor.app.DriveGuard.domain.model.Photo;
import com.monitor.app.DriveGuard.domain.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo savePhoto(String tripId, byte[] photoData) {
        Photo photo = new Photo();
        photo.setTripId(tripId);
        photo.setData(photoData);
        return photoRepository.save(photo);
    }

    public Photo getPhotoById(String id) {
        return photoRepository.findById(id).orElseThrow(() -> new RuntimeException("Photo not found"));
    }
}
