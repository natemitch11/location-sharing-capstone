package com.devmountain.locationserver.repositories;

import com.devmountain.locationserver.dto.UserDto;
import com.devmountain.locationserver.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void saveNewUser(UserDto userDto);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    //Get User Info
    List<String> getUserInfo(String username);

    //Delete User
    String deleteUser(String username);
}
