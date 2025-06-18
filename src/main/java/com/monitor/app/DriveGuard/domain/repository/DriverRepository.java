package com.monitor.app.DriveGuard.domain.repository;

import com.monitor.app.DriveGuard.domain.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface DriverRepository extends MongoRepository<Driver, String> {
    UserDetails findByEmail(String email);
}

