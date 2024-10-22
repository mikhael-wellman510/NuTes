package com.example.Nutech_Integration.Repository.Auth;

import com.example.Nutech_Integration.Entity.Auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email);

}
