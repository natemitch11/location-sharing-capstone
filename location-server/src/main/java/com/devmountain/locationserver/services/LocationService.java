package com.devmountain.locationserver.services;

import com.devmountain.locationserver.dto.LocationDto;
import com.devmountain.locationserver.model.Location;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface LocationService {
    @Transactional
    String addNewLocation(Long deviceId, LocationDto locationDto);

    String removeLocationByDeviceAfterDate(Long locationDto, LocalDateTime dateTime);

    List<Location> getLocationsByDevice(Long deviceId);
}
