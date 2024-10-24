package com.example.Nutech_Integration.Controller;


import com.example.Nutech_Integration.Constant.AppPath;
import com.example.Nutech_Integration.DTO.Request.ServicePPOBRequest;
import com.example.Nutech_Integration.DTO.Response.CommonResponse;
import com.example.Nutech_Integration.DTO.Response.ServicePPOBResponse;
import com.example.Nutech_Integration.Service.ServicePPOBService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.SERVICES)
public class ServicePPOBController {

    private final ServicePPOBService servicePPOBService;

    @GetMapping("")
    ResponseEntity<?> findAllServicePPOB(){


        List<ServicePPOBResponse> serviceResponse = servicePPOBService.findAllService();



        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<ServicePPOBResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sukses")
                        .data(serviceResponse)
                        .build()
                );

    }

    @PostMapping("/addService")
    ResponseEntity<?> addServicePPOB(@RequestBody List<ServicePPOBRequest> servicePPOBRequests){

        List<ServicePPOBResponse> servicePPOBResponseList = servicePPOBService.addAllService(servicePPOBRequests);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<ServicePPOBResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sukses")
                        .data(servicePPOBResponseList)
                        .build()
                );
    }
}
