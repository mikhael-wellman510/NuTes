package com.example.Nutech_Integration.Service.Impl;

import com.example.Nutech_Integration.DTO.Response.BannerResponse;
import com.example.Nutech_Integration.Entity.Banner;
import com.example.Nutech_Integration.Entity.ServicePPOB;
import com.example.Nutech_Integration.Helper.UserInfo;
import com.example.Nutech_Integration.Repository.BannerRepository;

import com.example.Nutech_Integration.Service.BannerService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {


    private final BannerRepository bannerRepository;

    @Override
    public List<BannerResponse> findAllBanner() {
        List<Banner> getAllBanner = bannerRepository.findAllBanner();




        return getAllBanner.stream().map(val -> BannerResponse.builder()
                .banner_name(val.getBannerName())
                .banner_image(val.getBannerImage())
                .description(val.getDescription())
                .build()).toList();
    }

}
