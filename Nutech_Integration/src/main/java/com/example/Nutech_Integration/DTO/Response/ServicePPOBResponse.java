package com.example.Nutech_Integration.DTO.Response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ServicePPOBResponse {

    private String service_code;
    private String service_name;
    private String service_icon;
    private Integer service_tarif;

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "service_code='" + service_code + '\'' +
                ", service_name='" + service_name + '\'' +
                ", service_icon='" + service_icon + '\'' +
                ", service_tarif=" + service_tarif +
                '}';
    }
}
