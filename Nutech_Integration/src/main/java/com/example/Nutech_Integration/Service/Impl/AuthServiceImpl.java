package com.example.Nutech_Integration.Service.Impl;

import com.example.Nutech_Integration.Constant.Auth.Erole;
import com.example.Nutech_Integration.DTO.Request.Auth.AuthRequest;
import com.example.Nutech_Integration.DTO.Response.Auth.LoginResponse;
import com.example.Nutech_Integration.DTO.Response.Auth.RegisterResponse;
import com.example.Nutech_Integration.Entity.Auth.AppUser;
import com.example.Nutech_Integration.Entity.Auth.User;
import com.example.Nutech_Integration.Repository.Auth.UserRepository;
import com.example.Nutech_Integration.SecurityJWT.JwtUtil;
import com.example.Nutech_Integration.Service.Auth.AuthService;
import com.example.Nutech_Integration.Utillity.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public RegisterResponse registerUser(AuthRequest authRequest) {


        try {

            User user = User.builder()
                    .email(authRequest.getEmail())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .firstName(authRequest.getFirst_name())
                    .lastName(authRequest.getLast_name())
                    .erole(Erole.ROLE_USER)
                    .build();

            userRepository.save(user);


            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User Already exist");
        }


    }

    @Override
    public LoginResponse loginUser(AuthRequest authRequest) {


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail().toLowerCase(),
                authRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Authentication authentications = SecurityContextHolder.getContext().getAuthentication();

        AppUser appUser = (AppUser) authentication.getPrincipal();

        String token = jwtUtil.generateToken(appUser);
        String email = jwtUtil.getUserInfoByToken(token).get("email");


        return LoginResponse.builder()
                .token(token)
                .build();


    }
}
