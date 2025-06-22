package com.monitor.app.DriveGuard.interfaces.controller;

import com.monitor.app.DriveGuard.application.service.TripService;
import com.monitor.app.DriveGuard.domain.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping
    public Trip startTrip(@RequestBody TripRequest request) {
        System.out.printf("Driver id: %s%n",request.driverId);
        return tripService.startTrip(request.driverId);
    }

    @GetMapping("/{driverId}")
    public List<Trip> getTripsByDriver(@PathVariable String driverId) {
        return tripService.getTripsByDriver(driverId);
    }

    @PutMapping("/end/{tripId}")
    public Trip endTrip(@PathVariable String tripId) {
        return tripService.endTrip(tripId);
    }

    public record TripRequest(String driverId, String tripId){}
}
