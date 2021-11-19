package com.devmountain.locationserver.services;

import com.devmountain.locationserver.dto.DeviceDto;
import com.devmountain.locationserver.model.Device;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface DeviceService {
    @Transactional
    DeviceDto addNewDevice(DeviceDto deviceDto);

    @Transactional
    String removeDevice(Long deviceDto);

    Optional<List<Device>> getDeviceInfo(Long deviceId);

    @Transactional
    DeviceDto updateDevice(Long id, DeviceDto deviceDto);
}
