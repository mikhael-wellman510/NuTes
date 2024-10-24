package com.example.Nutech_Integration.Repository;

import com.example.Nutech_Integration.Entity.Auth.User;
import com.example.Nutech_Integration.Entity.Transaction;
import com.example.Nutech_Integration.InterfaceQuery.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query(value = "select u.id , u.email , u.first_name as firstName , u.last_name as lastName , u.balance from m_user as u where email = :email", nativeQuery = true)
    UserProfile findByEmail(@Param("email") String email);
    
}
