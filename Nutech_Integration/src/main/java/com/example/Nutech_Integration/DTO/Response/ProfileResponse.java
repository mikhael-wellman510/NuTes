package com.example.Nutech_Integration.DTO.Response;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProfileResponse {
    private String email ;
    private String first_name;
    private String last_name;
    private String profile_image;
}
