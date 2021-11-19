package com.devmountain.locationserver.controllers;

import com.devmountain.locationserver.dto.DeviceDto;
import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @PostMapping
    public DeviceDto addNewDevice(@RequestBody DeviceDto deviceDto) {
        return deviceService.addNewDevice(deviceDto);
    }

    @PutMapping("/{id}")
    public DeviceDto updateDevice(@PathVariable("id") Long deviceId, @RequestBody DeviceDto deviceDto) {
        return deviceService.updateDevice(deviceId, deviceDto);
    }

    @GetMapping("/{id}")
    public Optional<List<Device>> getDeviceInfo(@PathVariable("id") Long deviceId) {
        return deviceService.getDeviceInfo(deviceId);
    }

    @DeleteMapping("/{id}")
    public String deleteDevice(@PathVariable("id") Long deviceId) {
        return deviceService.removeDevice(deviceId);
    }

}
