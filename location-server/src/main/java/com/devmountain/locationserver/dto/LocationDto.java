package com.devmountain.locationserver.dto;

import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.model.Location;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocationDto {
    private Long id;
    private float latitude;
    private float longitude;
    private Device device;
    private LocalDateTime dateTime;

    public LocationDto(Location location) {
        this.id = location.getId();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.device = location.getDevice();
        this.dateTime = location.getDateTime();
    }

    public LocationDto(float latitude, float longitude, Device device) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.device = device;
    }
}
