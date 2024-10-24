package com.example.Nutech_Integration.Service;

import com.example.Nutech_Integration.DTO.Response.TransactionDetailResponse;
import com.example.Nutech_Integration.Entity.TransactionDetail;
import org.springframework.data.domain.Page;

public interface TransactionDetailService {

    TransactionDetail saveTransaction(TransactionDetail transactionDetail);
    Page<TransactionDetailResponse> historyTransaction(Integer offset , Integer limit);
}
