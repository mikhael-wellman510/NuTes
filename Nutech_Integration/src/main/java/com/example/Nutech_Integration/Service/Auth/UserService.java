package com.example.Nutech_Integration.Service.Auth;

import com.example.Nutech_Integration.Entity.Auth.AppUser;
import com.example.Nutech_Integration.Entity.Auth.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserById(String id);

    User findUserById(String id);
    User saveUser(User user);
}
