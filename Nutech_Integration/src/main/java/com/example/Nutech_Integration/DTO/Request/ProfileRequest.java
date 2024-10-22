package com.example.Nutech_Integration.DTO.Request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProfileRequest {
    private String first_name;
    private String last_name;
    private MultipartFile profilePicture;

    @Override
    public String toString() {
        return "ProfileRequest{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", profilePicture=" + profilePicture +
                '}';
    }
}
