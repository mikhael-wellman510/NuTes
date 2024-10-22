package com.example.Nutech_Integration.Repository.Auth;

import com.example.Nutech_Integration.Entity.Auth.User;
import com.example.Nutech_Integration.InterfaceQuery.UpdateProfile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query(value = "select m.email as email , m.first_name as firstName , m.last_name as lastName , m.profile_Image as profileImage from m_user as m where email = :email", nativeQuery = true)
    UpdateProfile getProfile(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "update m_user set first_name = :firstName , last_name = :lastName where email =:email" , nativeQuery = true )
    void updateProfile(@Param("firstName") String firstName, @Param("lastName")String lastName , @Param("email")String email);

    @Query(value = "select * from m_user where email = :email" , nativeQuery = true)
    User findByEmailUser(@Param("email") String email);
}
