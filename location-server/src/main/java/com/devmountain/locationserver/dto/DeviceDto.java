package com.devmountain.locationserver.dto;

import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.model.Location;
import lombok.Data;

import java.util.List;

@Data
public class DeviceDto {
    private Long id;
    private String name;
    private String classification;
    private List<Location> locations;

    public DeviceDto(Device device) {
        this.id = device.getId();
        this.name = device.getName();
        this.classification = device.getClassification();
        this.locations = device.getLocations();
    }

    public DeviceDto(String name, String classification, List<Location> locations) {
        this.name = name;
        this.classification = classification;
        this.locations = locations;
    }
}
