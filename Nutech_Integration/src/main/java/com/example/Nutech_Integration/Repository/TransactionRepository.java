package com.example.Nutech_Integration.Repository;

import com.example.Nutech_Integration.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {


}
