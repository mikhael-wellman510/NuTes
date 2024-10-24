package com.example.Nutech_Integration.Controller;

import com.example.Nutech_Integration.Constant.AppPath;
import com.example.Nutech_Integration.DTO.Request.Auth.AuthRequest;
import com.example.Nutech_Integration.DTO.Response.Auth.LoginResponse;
import com.example.Nutech_Integration.DTO.Response.Auth.RegisterResponse;
import com.example.Nutech_Integration.DTO.Response.CommonResponse;
import com.example.Nutech_Integration.DTO.Response.ProfileResponse;
import com.example.Nutech_Integration.Service.Auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor

@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/registration")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRequest authRequest) {
        RegisterResponse registerResponse = authService.registerUser(authRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<RegisterResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Registrasi Berhasil silahkan Login")
                        .data(null)
                        .build()
                );
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody AuthRequest authRequest) {

        LoginResponse loginResponse = authService.loginUser(authRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<LoginResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Login Sukses")
                        .data(loginResponse)
                        .build()
                );


    }

}
