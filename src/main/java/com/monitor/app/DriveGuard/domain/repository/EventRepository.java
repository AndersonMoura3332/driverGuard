package com.monitor.app.DriveGuard.domain.repository;


import com.monitor.app.DriveGuard.domain.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findAllByTripId(String tripId);
}
