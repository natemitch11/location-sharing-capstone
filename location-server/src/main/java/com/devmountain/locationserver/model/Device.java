package com.devmountain.locationserver.model;

import com.devmountain.locationserver.dto.DeviceDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Devices")
@Data
public class Device {
    public Device() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "CLASSIFICATION", nullable = false, length = 15)
    private String classification;

    @OneToMany(mappedBy = "device")
    private List<Location> locations = new ArrayList<>();

    @ManyToMany(mappedBy = "deviceSet", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    Set<User> userSet = new HashSet<>();

    public Device(DeviceDto deviceDto) {
        this.name = deviceDto.getName();
        this.classification = deviceDto.getType();
    }
}
