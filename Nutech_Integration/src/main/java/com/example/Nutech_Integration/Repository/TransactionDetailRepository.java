package com.example.Nutech_Integration.Repository;

import com.example.Nutech_Integration.Entity.TransactionDetail;
import com.example.Nutech_Integration.InterfaceQuery.TransactionDetailsProjections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail , String> {


    @Query(value = "select td.invoice_number as invoiceNumber , td.transaction_type as transactionType, sp.service_name as description , td.amount as  totalAmount , t.created_on as createdOn from t_transaction_detail as td \n" +
            "join m_transaction as t on td.transaction_id = t.id \n" +
            "join m_user as u on t.user_id = u.id\n" +
            "join m_service_ppob as sp on td.service_ppob_id = sp.id where email = :email" , nativeQuery = true)
    Page<TransactionDetailsProjections> getDetailsTransactionHistory(@Param("email") String email , Pageable pageable);

}
