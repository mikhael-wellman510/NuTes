package com.example.Nutech_Integration.DTO.Request.Auth;

import jakarta.validation.constraints.Email;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AuthRequest {
    private String id;

    @Email(message = "Parameter Email tidak sesuai format")
    private String email;
    private String first_name;
    private String last_name;
    private String password;


}
