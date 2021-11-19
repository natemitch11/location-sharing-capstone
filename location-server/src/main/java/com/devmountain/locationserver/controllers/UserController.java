package com.devmountain.locationserver.controllers;

import com.devmountain.locationserver.dto.UserDto;
import com.devmountain.locationserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //GET REQUESTS
    @GetMapping("/{username}")
    public List<String> getUserInfo(@PathVariable String username) {
        return userService.getUserInfo(username);
    }

    //POST REQUESTS
    @PostMapping
    public UserDto postNewUser(@RequestBody UserDto userDto) {
        userService.saveNewUser(userDto);
        return userDto;
    }

    //PUT REQUESTS
    @PutMapping("/{username}")
    public UserDto updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
        userService.updateUser(username, userDto);
        return userDto;
    }

    //DELETE REQUESTS
    @DeleteMapping("/{username}")
    public String removeUser(@PathVariable String username) {
        userService.removeUser(username);
        return "User Removed";
    }
}
