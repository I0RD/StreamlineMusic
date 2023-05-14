package com.app.StreamlineMusic.service.impl;

import com.app.StreamlineMusic.StreamlineMusicApplication;
import com.app.StreamlineMusic.entity.Role;
import com.app.StreamlineMusic.entity.User;
import com.app.StreamlineMusic.exception.StreamlineAPIException;
import com.app.StreamlineMusic.repository.RoleRepository;
import com.app.StreamlineMusic.repository.UserRepository;
import com.app.StreamlineMusic.service.AuthService;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

private AuthenticationManager authenticationManager;
private UserRepository userRepository;
private RoleRepository roleRepository;
private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication.isAuthenticated();
    }
    @Transactional
    public String register(String name,String userName,String email, String password) {

        //add check for username exists in database
        if(userRepository.existsByUsername(userName)){
            throw new StreamlineAPIException(HttpStatus.BAD_REQUEST,"Username is already exists!.");
        }
        //add check for email exists in database
        if(userRepository.existsByEmail(email)){
            throw new StreamlineAPIException(HttpStatus.BAD_REQUEST,"Email is already exists!.");
        }

        User user = new User();
        user.setName(name);
        user.setUsername(userName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        Set<Role> roles=new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully";
    }
}
