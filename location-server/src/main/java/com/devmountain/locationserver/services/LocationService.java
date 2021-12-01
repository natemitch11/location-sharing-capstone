package com.devmountain.locationserver.services;

import com.devmountain.locationserver.dto.LocationDto;
import com.devmountain.locationserver.model.Location;

import java.time.LocalDateTime;
import java.util.List;

public interface LocationService {
    String addNewLocation(Long deviceId, LocationDto locationDto);

    String removeLocationByDeviceAfterDate(Long locationDto, LocalDateTime dateTime);

    List<Location> getLocationsByDevice(Long deviceId);

    List<Location> getAllUserDeviceLocations(String username);
}
