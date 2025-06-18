package com.monitor.app.DriveGuard.domain.repository;

import com.monitor.app.DriveGuard.domain.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PhotoRepository extends MongoRepository<Photo, String> {
    Optional<Photo> findByTripId(String tripId);
}
