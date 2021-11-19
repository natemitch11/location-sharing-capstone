package com.devmountain.locationserver.repositories;

import com.devmountain.locationserver.dto.LocationDto;
import com.devmountain.locationserver.model.Location;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    String addNewLocation(LocationDto locationDto);

    Optional<Location> findById(Long id);

    String removeLocationByDeviceAfterDate(Long locationDto, LocalDateTime dateTime);

    List<Location> getLocationsByDevice(Long deviceId);
}
