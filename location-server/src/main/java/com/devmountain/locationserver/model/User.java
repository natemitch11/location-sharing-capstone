package com.devmountain.locationserver.model;

import com.devmountain.locationserver.dto.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Users")
@Data
public class User {
    public User() {
    }

    public User(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.passhash = userDto.getPasshash();
        this.deviceSet = userDto.getDeviceSet();
        this.username = userDto.getUsername();
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Column(name = "EMAIL", unique = true, length = 75, nullable = false)
    private String email;

    @Column(name = "USERNAME", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "PASSHASH", nullable = false)
    private String passhash;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID", "DEVICE_ID"})},
            name = "UsersDevices",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "DEVICE_ID")}
    )
    @EqualsAndHashCode.Exclude
    private Set<Device> deviceSet = new HashSet<>();

    public User(String firstName, String lastName, String email, String username, String passhash, Set<Device> deviceSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.passhash = passhash;
        this.deviceSet = deviceSet;
    }

    public void addDevice(Device device) {
        this.deviceSet.add(device);
        device.getUserSet().add(this);
    }

    public void removeDevice(Device device) {
        this.deviceSet.remove(device);
        device.getUserSet().remove(this);
    }
}
