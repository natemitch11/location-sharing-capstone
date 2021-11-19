package com.devmountain.locationserver.repositories;

import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.model.User;

import java.util.List;

public interface UsersDevicesRepository {
    List<Device> getUserDevices(Long userId);

    void addDeviceToUser(User user, Device device);

    void removeDeviceFromUser(User user, Device device);
}
