package com.monitor.app.DriveGuard.application.service;

import com.monitor.app.DriveGuard.domain.model.Event;
import com.monitor.app.DriveGuard.domain.model.Photo;
import com.monitor.app.DriveGuard.domain.model.Trip;
import com.monitor.app.DriveGuard.domain.repository.EventRepository;
import com.monitor.app.DriveGuard.domain.repository.PhotoRepository;
import com.monitor.app.DriveGuard.domain.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    EventRepository eventRepository;

    public Trip startTrip(String driverId) {
        Trip trip = new Trip();
        trip.setDriverId(driverId);
        trip.setStartTime(LocalDateTime.now());
        return tripRepository.save(trip);
    }

    public Trip endTrip(String tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        Photo photo = photoRepository.findByTripId(tripId).orElseThrow(() -> new RuntimeException("Photo not found"));
        List<Event> eventList = eventRepository.findAllByTripId(tripId);
        trip.setPhotoId(photo.getId());
        trip.setEventIds(eventList.stream().map(Event::getId).toList());
        trip.setEndTime(LocalDateTime.now());
        return tripRepository.save(trip);
    }

    public List<Trip> getTripsByDriver(String driverId) {
        return tripRepository.findByDriverId(driverId);
    }
}

