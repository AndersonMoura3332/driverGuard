package com.monitor.app.DriveGuard.domain.model;

import com.monitor.app.DriveGuard.application.dto.EventRequest;
import com.monitor.app.DriveGuard.domain.enums.EventType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Event {
    @Id
    private String id;
    private String tripId;
    private EventType type;
    private LocalDateTime timestamp;
    private Localization localization;
    private Double speed;

    public static Event of(EventRequest request){
        Event event = new Event();
        event.setType(request.eventType());
        event.setTripId(request.tripId());
        event.setTimestamp(LocalDateTime.now());
        event.setLocalization(request.localization());
        if (request.speed() != null)
            event.setSpeed(request.speed());
        return event;

    }



    public record Localization (Double latitude, Double longitude) {}

}
