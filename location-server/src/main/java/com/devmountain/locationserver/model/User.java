package com.devmountain.locationserver.model;

import com.devmountain.locationserver.dto.UserDto;
import com.devmountain.locationserver.request.RegisterReq;
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
        this.password = userDto.getPassword();
        this.deviceSet = userDto.getDeviceSet();
        this.username = userDto.getUsername();
    }

    public User(RegisterReq registerReq) {
        this.firstName = registerReq.getFirstName();
        this.lastName = registerReq.getLastName();
        this.email = registerReq.getEmail();
        this.password = registerReq.getPassword();
        this.username = registerReq.getUsername();
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

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "enabled")
    private boolean enabled = true;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID", "DEVICE_ID"})},
            name = "UsersDevices",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "DEVICE_ID")}
    )
    @EqualsAndHashCode.Exclude
    private Set<Device> deviceSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String firstName, String lastName, String email, String username, String password, Set<Device> deviceSet, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.deviceSet = deviceSet;
        this.roles = roles;
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
