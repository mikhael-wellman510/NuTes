package com.example.Nutech_Integration.Controller;

import com.example.Nutech_Integration.Constant.AppPath;
import com.example.Nutech_Integration.DTO.Response.BannerResponse;
import com.example.Nutech_Integration.DTO.Response.CommonResponse;
import com.example.Nutech_Integration.Entity.Banner;
import com.example.Nutech_Integration.Service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.Banner)
public class BannerController {
    private final BannerService bannerService;

    @GetMapping("")
    ResponseEntity<?> findAllBanner() {
        List<BannerResponse> bannerResponse = bannerService.findAllBanner();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<BannerResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sukses")
                        .data(bannerResponse)
                        .build()
                );
    }


}
