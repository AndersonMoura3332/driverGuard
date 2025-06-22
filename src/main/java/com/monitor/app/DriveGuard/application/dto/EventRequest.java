package com.monitor.app.DriveGuard.application.dto;

import com.monitor.app.DriveGuard.domain.enums.EventType;
import com.monitor.app.DriveGuard.domain.model.Event;

public record EventRequest (String tripId, EventType eventType, Event.Localization localization, Double speed){




}
