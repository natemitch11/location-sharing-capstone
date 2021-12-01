package com.devmountain.locationserver.security.services;

import com.devmountain.locationserver.model.User;
import com.devmountain.locationserver.repositories.RoleRepository;
import com.devmountain.locationserver.repositories.UserRepository;
import com.devmountain.locationserver.request.RegisterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User not found" + s));
        return UserDetailsImpl.build(user);
    }

    @Transactional
    public void saveNewUser(RegisterReq registerReq) {
        Optional<User> user = userRepository.saveNewUser(registerReq);
        user.ifPresent(UserDetailsImpl::build);
    }

    public UserDetails build(User value) {
        return UserDetailsImpl.build(value);
    }
}
