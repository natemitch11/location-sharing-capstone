package com.devmountain.locationserver.dto;

import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.model.User;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private Set<Device> deviceSet;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.deviceSet = user.getDeviceSet();
    }

    public UserDto(String firstName, String lastName, String email, String username, String password, Set<Device> deviceSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.deviceSet = deviceSet;
    }

    public UserDto() {
    }
}
