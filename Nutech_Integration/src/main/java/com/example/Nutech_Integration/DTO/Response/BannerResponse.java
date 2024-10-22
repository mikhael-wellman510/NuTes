package com.example.Nutech_Integration.DTO.Response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BannerResponse {
    private String banner_name;
    private String banner_image;
    private String description;

    @Override
    public String toString() {
        return "BannerResponse{" +
                "banner_name='" + banner_name + '\'' +
                ", banner_image='" + banner_image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
