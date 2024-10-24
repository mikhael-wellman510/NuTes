package com.example.Nutech_Integration.Repository;

import com.example.Nutech_Integration.Entity.ServicePPOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Repository
public interface ServicePPOBRepository extends JpaRepository<ServicePPOB,String> {
    @Query(value = "select * from m_service_ppob" , nativeQuery = true)
    List<ServicePPOB> getAllData();

    @Query(value = "select * from m_service_ppob where service_code = :service_code" , nativeQuery = true)
    ServicePPOB getDetailServiceByCode(@Param("service_code") String serviceCode);
}

