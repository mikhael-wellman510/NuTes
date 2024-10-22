package com.example.Nutech_Integration.Repository;

import com.example.Nutech_Integration.Entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceRepository extends JpaRepository<Service,String> {
}
