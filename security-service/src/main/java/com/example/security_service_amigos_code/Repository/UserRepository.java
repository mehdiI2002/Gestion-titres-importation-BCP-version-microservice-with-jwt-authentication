package com.example.security_service_amigos_code.Repository;

import com.example.security_service_amigos_code.user.Role;
import com.example.security_service_amigos_code.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail (String email);

}
