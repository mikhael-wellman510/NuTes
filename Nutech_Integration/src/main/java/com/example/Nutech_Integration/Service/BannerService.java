package com.example.Nutech_Integration.Service;

import com.example.Nutech_Integration.DTO.Response.BannerResponse;

import java.util.List;

public interface BannerService {
    List<BannerResponse> findAllBanner();
}
