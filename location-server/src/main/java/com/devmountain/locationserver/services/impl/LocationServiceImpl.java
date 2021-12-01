package com.devmountain.locationserver.services.impl;

import com.devmountain.locationserver.dto.LocationDto;
import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.model.Location;
import com.devmountain.locationserver.repositories.DeviceRepository;
import com.devmountain.locationserver.repositories.LocationRepository;
import com.devmountain.locationserver.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    @Transactional
    public String addNewLocation(Long deviceId, LocationDto locationDto) {
        Optional<Device> device = deviceRepository.findById(deviceId);
        device.ifPresent(locationDto::setDevice);
        return locationRepository.addNewLocation(locationDto);
    }

    @Override
    public String removeLocationByDeviceAfterDate(Long deviceId, LocalDateTime dateTime) {
        return locationRepository.removeLocationByDeviceAfterDate(deviceId, dateTime);
    }

    @Override
    public List getLocationsByDevice(Long deviceId) {
        return locationRepository.getLocationsByDevice(deviceId);
    }

    @Override
    public List<Location> getAllUserDeviceLocations(String username) {
        return locationRepository.getAllUserDeviceLocations(username);
    }
}
