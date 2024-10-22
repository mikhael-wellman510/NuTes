package com.example.Nutech_Integration.Service;

import com.example.Nutech_Integration.DTO.Request.ProfileRequest;
import com.example.Nutech_Integration.DTO.Response.ProfileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    ProfileResponse profile();
    ProfileResponse updateProfile(ProfileRequest profileRequest);
    ProfileResponse updateImageProfile(MultipartFile file);
}
