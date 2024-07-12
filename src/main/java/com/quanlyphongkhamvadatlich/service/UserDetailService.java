package com.quanlyphongkhamvadatlich.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.repository.UserRepository;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserPrincipal loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        
        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new UserPrincipal(user.get());
    }

}
