package com.example.Nutech_Integration.Service;

import com.example.Nutech_Integration.DTO.Request.TransactionRequest;
import com.example.Nutech_Integration.DTO.Response.BalanceResponse;
import com.example.Nutech_Integration.DTO.Response.TransactionResponse;

public interface TransactionService {
    BalanceResponse getBalance();
    BalanceResponse topUp(TransactionRequest transactionRequest);

    TransactionResponse transaction(TransactionRequest transactionRequest);
}
