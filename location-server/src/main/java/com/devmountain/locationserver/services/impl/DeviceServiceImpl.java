package com.devmountain.locationserver.services.impl;

import com.devmountain.locationserver.dto.DeviceDto;
import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.repositories.DeviceRepository;
import com.devmountain.locationserver.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    @Transactional
    public DeviceDto addNewDevice(DeviceDto deviceDto) {
        deviceRepository.addNewDevice(deviceDto);
        return deviceDto;
    }

    @Override
    @Transactional
    public String removeDevice(Long deviceId) {
        deviceRepository.removeDevice(deviceId);
        return "Device Removed";
    }

    @Override
    public Optional<List<Device>> getDeviceInfo(Long deviceId) {
        return deviceRepository.getDeviceInfo(deviceId);
    }

    @Override
    @Transactional
    public DeviceDto updateDevice(Long id, DeviceDto deviceDto) {
        Optional<Device> device = deviceRepository.findById(id);
        device.ifPresent(value -> {
            value.setClassification(deviceDto.getType() == null ? value.getClassification() : deviceDto.getType());
            value.setName(deviceDto.getName() == null ? value.getName() : deviceDto.getName());
        });
        return deviceDto;
    }
}
