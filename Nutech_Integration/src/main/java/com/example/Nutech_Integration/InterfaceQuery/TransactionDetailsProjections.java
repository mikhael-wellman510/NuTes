package com.example.Nutech_Integration.InterfaceQuery;

import com.example.Nutech_Integration.Constant.TransactionType;

import java.time.LocalDateTime;

public interface TransactionDetailsProjections {

    String getInvoiceNumber();
    TransactionType getTransactionType();
    String getDescription();
    Integer getTotalAmount();
    LocalDateTime getCreatedOn();
}
