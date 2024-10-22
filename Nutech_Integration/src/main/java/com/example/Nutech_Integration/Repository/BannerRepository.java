package com.example.Nutech_Integration.Repository;

import com.example.Nutech_Integration.Entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository  extends JpaRepository<Banner , String> {

    @Query(value = "select mb.id , mb.banner_image , mb.banner_name , mb.description from m_banner as mb " , nativeQuery = true)
    List<Banner> findAllBanner();

}
