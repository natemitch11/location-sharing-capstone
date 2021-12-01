package com.devmountain.locationserver.services;

import com.devmountain.locationserver.dto.UserDto;
import com.devmountain.locationserver.model.User;
import com.devmountain.locationserver.request.RegisterReq;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto saveNewUser(UserDto userDto);

    Optional<User> saveNewUser(RegisterReq registerReq);

    void updateUser(String username, UserDto userDto);

    void removeUser(String username);

    List<String> getUserInfo(String username);

    Optional<User> findByUsername(String username);

    void saveAndFlush(User value);
}
