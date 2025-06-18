package com.monitor.app.DriveGuard.interfaces.controller;

import com.monitor.app.DriveGuard.application.service.EventService;
import com.monitor.app.DriveGuard.domain.enums.EventType;
import com.monitor.app.DriveGuard.domain.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> logEvent(@RequestParam String tripId, @RequestParam EventType type) {
        Event event = eventService.logEvent(tripId, type);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Event>> getEventsByTrip(@PathVariable String tripId) {
        List<Event> events = eventService.getEventsByTrip(tripId);
        return ResponseEntity.ok(events);
    }
}
