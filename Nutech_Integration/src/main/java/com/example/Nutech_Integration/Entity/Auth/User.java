package com.example.Nutech_Integration.Entity.Auth;

import com.example.Nutech_Integration.Constant.Auth.Erole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_user")
@Builder(toBuilder = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Faktor email kah ?
    @Column(nullable = false, length = 100 , unique = true)
    //    @Email
    private String email;

    @Column(name = "first_name", nullable = false , length = 100 )
    private String firstName;

    @Column(name = "last_name" , nullable = false , length = 100)
    private String lastName;

    @Column(nullable = false , length = 100)
    private String password;

    @Column(name = "profil_image", length = 100)
    private String profilImage;

    @Enumerated(EnumType.STRING)
    private Erole erole;

    private Integer balance;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", profilImage='" + profilImage + '\'' +
                ", balance=" + balance +
                '}';
    }
}
