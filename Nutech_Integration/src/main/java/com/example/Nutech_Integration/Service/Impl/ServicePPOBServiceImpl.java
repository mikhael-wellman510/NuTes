package com.example.Nutech_Integration.Service.Impl;

import com.example.Nutech_Integration.DTO.Request.ServicePPOBRequest;
import com.example.Nutech_Integration.DTO.Response.ServicePPOBResponse;
import com.example.Nutech_Integration.Entity.ServicePPOB;
import com.example.Nutech_Integration.Repository.ServicePPOBRepository;
import com.example.Nutech_Integration.Service.ServicePPOBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicePPOBServiceImpl implements ServicePPOBService {

    private final ServicePPOBRepository servicePPOBRepository;

    @Override
    public List <ServicePPOBResponse> findAllService() {

        List<ServicePPOB> findAllServices = servicePPOBRepository.getAllData();
        System.out.println(findAllServices);
        return findAllServices.stream().map(val -> ServicePPOBResponse.builder()
                   .service_code(val.getServiceCode())
                   .service_name(val.getServiceName())
                   .service_icon(val.getServiceIcon())
                   .service_tarif(val.getServiceTarif())
                    .build()).toList();
    }

    @Override
    public List<ServicePPOBResponse> addAllService(List<ServicePPOBRequest> getData) {

        List<ServicePPOB> add = getData.stream().map(val -> ServicePPOB.builder()
                .serviceCode(val.getService_code())
                .serviceName(val.getService_name())
                .serviceIcon(val.getService_icon())
                .serviceTarif(val.getService_tarif())
                .build()).toList();

       add = servicePPOBRepository.saveAll(add);


        return add.stream().map(val -> ServicePPOBResponse.builder()
                .service_code(val.getServiceCode())
                .service_name(val.getServiceName())
                .service_icon(val.getServiceIcon())
                .service_tarif(val.getServiceTarif())
                .build()).toList();
    }

    @Override
    public ServicePPOB findServicePPOBbyServiceCode(String serviceCode) {
        return servicePPOBRepository.getDetailServiceByCode(serviceCode);
    }
}
