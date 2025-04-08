package com.example.security_service.service;

import com.example.security_service.Repository.UserRepository;
import com.example.security_service.user.Role;
import com.example.security_service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceDefault implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<String> getAllAdmins() {
      return  userRepository.findAllEmailsAdmins(Role.ADMIN);
    }

}
