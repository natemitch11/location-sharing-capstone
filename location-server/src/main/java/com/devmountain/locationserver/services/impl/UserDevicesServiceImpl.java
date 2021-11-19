package com.devmountain.locationserver.services.impl;

import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.model.User;
import com.devmountain.locationserver.repositories.DeviceRepository;
import com.devmountain.locationserver.repositories.UserRepository;
import com.devmountain.locationserver.repositories.UsersDevicesRepository;
import com.devmountain.locationserver.services.UserDevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserDevicesServiceImpl implements UserDevicesService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UsersDevicesRepository usersDevicesRepository;


    @Override
    public List<Device> getDeviceSet(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return usersDevicesRepository.getUserDevices(userOptional.get().getId());
        }
        return List.of();
    }

    @Override
    @Transactional
    public void addDeviceToUser(String username, Long deviceId) {
        Optional<Device> device = deviceRepository.findById(deviceId);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && device.isPresent()) {
            usersDevicesRepository.addDeviceToUser(user.get(), device.get());
        }
    }

    @Override
    @Transactional
    public void removeDeviceFromUser(String username, Long deviceId) {
        Optional<Device> device = deviceRepository.findById(deviceId);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && device.isPresent()) {
            usersDevicesRepository.removeDeviceFromUser(user.get(), device.get());
        }
    }
}
