package com.devmountain.locationserver.services;

import com.devmountain.locationserver.model.Device;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDevicesService {

    List<Device> getDeviceSet(String username);

    @Transactional
    void addDeviceToUser(String username, Long deviceId);

    @Transactional
    void removeDeviceFromUser(String username, Long deviceId);
}

