package com.example.Nutech_Integration.Service.Impl.AuthImpl;

import com.example.Nutech_Integration.Entity.Auth.AppUser;
import com.example.Nutech_Integration.Entity.Auth.User;
import com.example.Nutech_Integration.Repository.Auth.UserRepository;
import com.example.Nutech_Integration.Service.Auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public AppUser loadUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Invalid Credential"));

        return AppUser.builder()
                .id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .erole(user.getErole())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Invalid Email"));

        return AppUser.builder()
                .id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .erole(user.getErole())
                .build();
    }
}
