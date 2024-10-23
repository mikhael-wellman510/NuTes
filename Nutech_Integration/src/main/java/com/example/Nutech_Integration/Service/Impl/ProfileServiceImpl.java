package com.example.Nutech_Integration.Service.Impl;

import ch.qos.logback.core.util.StringUtil;
import com.example.Nutech_Integration.DTO.Request.ProfileRequest;
import com.example.Nutech_Integration.DTO.Response.ProfileResponse;
import com.example.Nutech_Integration.Entity.Auth.User;
import com.example.Nutech_Integration.Helper.UserInfo;
import com.example.Nutech_Integration.InterfaceQuery.UpdateProfile;
import com.example.Nutech_Integration.Repository.Auth.UserRepository;
import com.example.Nutech_Integration.Service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;


    @Override
    public ProfileResponse profile() {

        UserDetails userDetails = UserInfo.userInfo();

        UpdateProfile up = userRepository.getProfile(userDetails.getUsername());

        return ProfileResponse.builder()
                .email(userDetails.getUsername())
                .first_name(up.getFirstName())
                .last_name(up.getLastName())
                .profile_image(up.getProfileImage())
                .build();
    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest profileRequest) {
        UserDetails userDetails = UserInfo.userInfo();

        userRepository.updateProfile(profileRequest.getFirst_name(), profileRequest.getLast_name(), userDetails.getUsername());

        UpdateProfile up = userRepository.getProfile(userDetails.getUsername());

        return ProfileResponse.builder()
                .email(userDetails.getUsername())
                .first_name(up.getFirstName())
                .last_name(up.getLastName())
                .profile_image(up.getProfileImage())
                .build();
    }

    @Override
    @Transactional
    public ProfileResponse updateImageProfile(MultipartFile file) {


        try{
            String fileType = file.getContentType();
            if (fileType.endsWith("jpeg") || fileType.endsWith("jpg")){
                UserDetails userDetails = UserInfo.userInfo();
                User user = userRepository.findByEmailUser(userDetails.getUsername());
                System.out.println(fileType);
                String fileName = file.getOriginalFilename();
                user.setProfileImage(fileName);
                byte[] bytes = file.getBytes();
                User saveUser = userRepository.save(user);



                return ProfileResponse.builder()
                        .email(saveUser.getEmail())
                        .first_name(saveUser.getFirstName())
                        .last_name(saveUser.getLastName())
                        .profile_image(saveUser.getProfileImage())
                        .build();
            }else {
                throw new IllegalArgumentException("Format Image tidak sesuai");
            }



        }catch (IOException e){
            throw new IllegalArgumentException("Invalid");
        }



    }
}
