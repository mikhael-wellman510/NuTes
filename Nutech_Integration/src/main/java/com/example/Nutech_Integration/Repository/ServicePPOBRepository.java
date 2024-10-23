package com.example.Nutech_Integration.Repository;

import com.example.Nutech_Integration.Entity.ServicePPOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ServicePPOBRepository extends JpaRepository<ServicePPOB,String> {
    @Query(value = "select * from m_service_ppob" , nativeQuery = true)
    List<ServicePPOB> getAllData();
}
