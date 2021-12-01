package com.devmountain.locationserver.controllers;

import com.devmountain.locationserver.dto.LocationDto;
import com.devmountain.locationserver.model.Location;
import com.devmountain.locationserver.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @PostMapping("/{deviceId}")
    public String addNewLocation(@PathVariable("deviceId") Long deviceId, @RequestBody LocationDto locationDto) {
        return locationService.addNewLocation(deviceId, locationDto);
    }

    @DeleteMapping("/{deviceId}")
    public String deleteLocationDataByDevice(@PathVariable("deviceId") Long deviceId, @RequestBody LocalDateTime dateTime) {
        return locationService.removeLocationByDeviceAfterDate(deviceId, dateTime);
    }

    @GetMapping("/device/{deviceId}")
    public List<Location> getLocationsByDevice(@PathVariable("deviceId") Long deviceId) {
        return locationService.getLocationsByDevice(deviceId);
    }

    @GetMapping("/user/{username}")
    public List<Location> getAllUserDeviceLocations(@PathVariable("username") String username) {
        return locationService.getAllUserDeviceLocations(username);
    }
}
