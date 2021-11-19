package com.devmountain.locationserver.repositories;

import com.devmountain.locationserver.dto.DeviceDto;
import com.devmountain.locationserver.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository {
    Optional<Device> findById(Long id);

    void addNewDevice(DeviceDto deviceDto);

    void removeDevice(Long id);

    Optional<List<Device>> getDeviceInfo(Long deviceDto);
}
