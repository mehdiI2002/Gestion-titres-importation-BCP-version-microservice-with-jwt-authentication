package com.example.security_service.Repository;

import com.example.security_service.user.Role;
import com.example.security_service.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail (String email);
@Query("SELECT u.email FROM User u WHERE u.role = :role")
    List<String> findAllEmailsAdmins(Role role);

}
