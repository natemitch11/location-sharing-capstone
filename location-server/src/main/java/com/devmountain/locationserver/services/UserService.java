package com.devmountain.locationserver.services;

import com.devmountain.locationserver.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveNewUser(UserDto userDto);

    void updateUser(String username, UserDto userDto);

    void removeUser(String username);

    List<String> getUserInfo(String username);
}
