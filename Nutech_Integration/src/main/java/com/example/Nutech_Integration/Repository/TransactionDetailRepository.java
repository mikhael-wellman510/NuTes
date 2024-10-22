package com.example.Nutech_Integration.Repository;

import com.example.Nutech_Integration.Entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail , String> {
}
