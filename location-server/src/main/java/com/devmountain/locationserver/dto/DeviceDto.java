package com.devmountain.locationserver.dto;

import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.model.Location;
import lombok.Data;

import java.util.List;

@Data
public class DeviceDto {
    private Long id;
    private String name;
    private String type;
    private List<Location> locations;

    public DeviceDto(Device device) {
        this.id = device.getId();
        this.name = device.getName();
        this.type = device.getClassification();
        this.locations = device.getLocations();
    }

    public DeviceDto(String name, String type, List<Location> locations) {
        this.name = name;
        this.type = type;
        this.locations = locations;
    }
}
