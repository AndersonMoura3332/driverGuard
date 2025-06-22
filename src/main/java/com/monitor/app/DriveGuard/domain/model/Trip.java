package com.monitor.app.DriveGuard.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Trip {
    @Id
    private String id;
    private String driverId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String photoId;
    private List<String> eventIds;
}
