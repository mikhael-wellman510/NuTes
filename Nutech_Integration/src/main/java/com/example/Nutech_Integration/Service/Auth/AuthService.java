package com.example.Nutech_Integration.Service.Auth;

import com.example.Nutech_Integration.DTO.Request.Auth.AuthRequest;
import com.example.Nutech_Integration.DTO.Response.Auth.LoginResponse;
import com.example.Nutech_Integration.DTO.Response.Auth.RegisterResponse;
import com.example.Nutech_Integration.DTO.Response.ProfileResponse;

public interface AuthService {
    RegisterResponse registerUser(AuthRequest authRequest);
    LoginResponse loginUser(AuthRequest authRequest);


}
