package com.devmountain.locationserver.services.impl;

import com.devmountain.locationserver.dto.UserDto;
import com.devmountain.locationserver.model.User;
import com.devmountain.locationserver.repositories.UserRepository;
import com.devmountain.locationserver.request.RegisterReq;
import com.devmountain.locationserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDto saveNewUser(UserDto userDto) {
        userRepository.saveNewUser(userDto);
        return userDto;
    }

    @Override
    @Transactional
    public Optional<User> saveNewUser(RegisterReq registerReq) {
        return userRepository.saveNewUser(registerReq);
    }

    @Override
    @Transactional
    public void updateUser(String username, UserDto userDto) {
        Optional<User> user = userRepository.findByUsername(username);
        user.ifPresent(value -> {
            value.setFirstName(userDto.getFirstName() == null ? value.getFirstName() : userDto.getFirstName());
            value.setPassword(userDto.getPassword() == null ? value.getPassword() : userDto.getPassword());
            value.setLastName(userDto.getLastName() == null ? value.getLastName() : userDto.getLastName());
            value.setUsername(userDto.getUsername() == null ? value.getUsername() : userDto.getUsername());
            value.setEmail(userDto.getEmail() == null ? value.getEmail() : userDto.getEmail());
        });
    }

    @Override
    @Transactional
    public void removeUser(String username) {
        userRepository.deleteUser(username);
    }

    @Override
    public List<String> getUserInfo(String username) {
        return userRepository.getUserInfo(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void saveAndFlush(User value) {
        userRepository.saveAndFlush(value);
    }
}
