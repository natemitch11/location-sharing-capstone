package com.devmountain.locationserver.controllers;

import com.devmountain.locationserver.dto.DeviceDto;
import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.services.DeviceService;
import com.devmountain.locationserver.services.UserDevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/users/{username}/devices")
public class UsersDevicesController {
    @Autowired
    UserDevicesService userDevicesService;
    @Autowired
    DeviceService deviceService;

    @GetMapping
    public List<Device> getUserDevices(@PathVariable("username") String username) {
        return userDevicesService.getDeviceSet(username);
    }

    @PostMapping
    public String addDeviceToUser(@PathVariable("username") String username, @RequestBody DeviceDto deviceDto) {
        deviceService.addNewDevice(deviceDto);
        userDevicesService.addDeviceToUser(username, deviceDto.getId());
        return "Device Added to User: " + username;
    }

    @DeleteMapping
    public String deleteDeviceFromUser(@PathVariable("username") String username, @RequestBody DeviceDto deviceDto) {
        userDevicesService.removeDeviceFromUser(username, deviceDto.getId());
        return "Device removed from User: " + username;
    }
}
