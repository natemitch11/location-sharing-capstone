package com.devmountain.locationserver.model;

import com.devmountain.locationserver.dto.LocationDto;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "DeviceLocations")
@Data
public class Location {

    public Location() {
    }

    public Location(float latitude, float longitude, Device device) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.device = device;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "LAT", nullable = false, columnDefinition = "float8")
    private float latitude;

    @Column(name = "LONG", nullable = false, columnDefinition = "float8")
    private float longitude;

    @ManyToOne
    private Device device;

    @Column(name = "DATE_CREATED", nullable = false, updatable = false, insertable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime dateTime;

    public Location(LocationDto locationDto) {
        this.latitude = locationDto.getLatitude();
        this.longitude = locationDto.getLongitude();
        this.device = locationDto.getDevice();
    }
}
