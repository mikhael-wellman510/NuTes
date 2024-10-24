package com.example.Nutech_Integration.Controller;


import com.example.Nutech_Integration.Constant.AppPath;
import com.example.Nutech_Integration.DTO.Request.ProfileRequest;
import com.example.Nutech_Integration.DTO.Response.CommonResponse;
import com.example.Nutech_Integration.DTO.Response.ProfileResponse;
import com.example.Nutech_Integration.Service.Auth.AuthService;
import com.example.Nutech_Integration.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.PROFILE)
public class ProfileController {

    private final ProfileService profileService;


    @GetMapping("")
    public ResponseEntity<?> profile(){
        ProfileResponse profileResponse = profileService.profile();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<ProfileResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sukses")
                        .data(profileResponse)
                        .build()
                );
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileRequest profileRequest){

        ProfileResponse updateProfile = profileService.updateProfile(profileRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<ProfileResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sukses")
                        .data(updateProfile)
                        .build()
                );

    }

    @PutMapping("/image")
    public ResponseEntity<?> updateImageProfile(@RequestParam("file")MultipartFile file){
        System.out.println("ceee " + file);
        ProfileResponse updateProfileImage = profileService.updateImageProfile(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<ProfileResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sukses")
                        .data(updateProfileImage)
                        .build()
                );
    }
}
