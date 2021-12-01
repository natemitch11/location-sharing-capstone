package com.devmountain.locationserver.controllers;

import com.devmountain.locationserver.model.Role;
import com.devmountain.locationserver.model.User;
import com.devmountain.locationserver.repositories.RoleRepository;
import com.devmountain.locationserver.request.LoginReq;
import com.devmountain.locationserver.request.RegisterReq;
import com.devmountain.locationserver.response.JwtResponse;
import com.devmountain.locationserver.response.Message;
import com.devmountain.locationserver.security.config.JwtTokenHelper;
import com.devmountain.locationserver.security.services.UserDetailsImpl;
import com.devmountain.locationserver.security.services.UserDetailsServiceImpl;
import com.devmountain.locationserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth/users")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;


    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody LoginReq loginReq) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenHelper.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterReq registerReq) {
        if (userService.findByUsername(registerReq.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(new Message("Username already taken"));
        }
        registerReq.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        Optional<User> user = userService.saveNewUser(registerReq);

        Set<String> strRoles = registerReq.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = new Role("USER");
            roles.add(userRole);
            roleRepository.save(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = new Role("ADMIN");
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = new Role("MODERATOR");
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = new Role("USER");
                        roles.add(userRole);
                }
            });
        }

        user.ifPresent(value -> value.setRoles(roles));
        user.ifPresent(value -> userDetailsService.build(value));

        return ResponseEntity.ok(new Message("User registered successfully"));
    }

}
