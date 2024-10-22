package com.example.Nutech_Integration.DTO.Response.Auth;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterResponse {
    private String email;
    private String first_name;
    private String last_name;
    private String password;

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
