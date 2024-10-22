package com.example.Nutech_Integration.DTO.Response.Auth;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoginResponse {
    private String token;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
