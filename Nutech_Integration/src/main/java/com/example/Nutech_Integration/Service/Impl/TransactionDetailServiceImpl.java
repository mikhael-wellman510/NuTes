package com.example.Nutech_Integration.Service.Impl;

import com.example.Nutech_Integration.Entity.TransactionDetail;
import com.example.Nutech_Integration.Repository.TransactionDetailRepository;
import com.example.Nutech_Integration.Service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {

    private final TransactionDetailRepository transactionDetailRepository;
    @Override
    public TransactionDetail saveTransaction(TransactionDetail transactionDetail) {
        return transactionDetailRepository.save(transactionDetail);
    }
}
