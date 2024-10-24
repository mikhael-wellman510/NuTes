package com.example.Nutech_Integration.Service;

import com.example.Nutech_Integration.DTO.Request.ServicePPOBRequest;
import com.example.Nutech_Integration.DTO.Response.ServicePPOBResponse;
import com.example.Nutech_Integration.Entity.ServicePPOB;

import java.util.List;

public interface ServicePPOBService {

   List<ServicePPOBResponse> findAllService();
   List<ServicePPOBResponse> addAllService(List<ServicePPOBRequest> getData);

   ServicePPOB findServicePPOBbyServiceCode(String serviceCode);

}
