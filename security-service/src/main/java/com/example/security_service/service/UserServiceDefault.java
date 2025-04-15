package com.example.security_service.service;

import com.example.security_service.Repository.UserRepository;
import com.example.security_service.dto.UpdateUserDTO;
import com.example.security_service.dto.UserDTO;
import com.example.security_service.mappers.UserDTOMapper;
import com.example.security_service.models.AddUserRequest;
import com.example.security_service.user.Role;
import com.example.security_service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceDefault implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDTOMapper userDTOMapper;
    @Autowired private PasswordEncoder passwordEncoder;
    @Override
    public List<String> getAllAdmins() {
      return  userRepository.findAllEmailsAdmins(Role.ADMIN);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public void addUser(AddUserRequest request) {
        User user = new User(null, request.getFirstName(), request.getLastName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getRole());
        userRepository.save(user);
    }

}
