package com.monitor.app.DriveGuard.domain.repository;

import com.monitor.app.DriveGuard.domain.model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TripRepository extends MongoRepository<Trip, String> {
    List<Trip> findByDriverId(String driverId);
}

