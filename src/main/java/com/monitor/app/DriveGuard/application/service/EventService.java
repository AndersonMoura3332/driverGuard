package com.monitor.app.DriveGuard.application.service;

import com.monitor.app.DriveGuard.application.dto.EventRequest;
import com.monitor.app.DriveGuard.domain.enums.EventType;
import com.monitor.app.DriveGuard.domain.model.Event;
import com.monitor.app.DriveGuard.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event logEvent(EventRequest request) {
        Event event = Event.of(request);
        return eventRepository.save(event);
    }

    public List<Event> getEventsByTrip(String tripId) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getTripId().equals(tripId))
                .toList();
    }
}
