package com.example.Nutech_Integration.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_service")
@Builder(toBuilder = true)
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "service_code", nullable = false, length = 100)
    private String serviceCode;

    @Column(name = "service_name", nullable = false, length = 100)
    private String serviceName;

    @Column(name = "service_icon", nullable = false, length = 100)
    private String serviceIcon;

    @Column(name = "service_tarif", nullable = false, length = 100)
    private Integer serviceTarif;

    @Override
    public String toString() {
        return "Service{" +
                "id='" + id + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", serviceIcon='" + serviceIcon + '\'' +
                ", serviceTarif=" + serviceTarif +
                '}';
    }
}
