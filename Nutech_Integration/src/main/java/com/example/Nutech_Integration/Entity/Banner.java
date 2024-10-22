package com.example.Nutech_Integration.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_banner")
@Builder(toBuilder = true)
public class Banner {

    @Id
    @GeneratedValue
    private String id;

    @Column(name = "banner_name", nullable = false, length = 100)
    private String bannerName;

    @Column(name = "banner_image", nullable = false, length = 100)
    private String bannerImage;

    @Column(name = "description", nullable = false, length = 100)
    private String description;
}
