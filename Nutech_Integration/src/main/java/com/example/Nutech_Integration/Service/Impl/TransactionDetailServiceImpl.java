package com.example.Nutech_Integration.Service.Impl;

import com.example.Nutech_Integration.DTO.Response.TransactionDetailResponse;
import com.example.Nutech_Integration.Entity.TransactionDetail;
import com.example.Nutech_Integration.Helper.UserInfo;
import com.example.Nutech_Integration.InterfaceQuery.TransactionDetailsProjections;
import com.example.Nutech_Integration.Repository.TransactionDetailRepository;
import com.example.Nutech_Integration.Service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {

    private final TransactionDetailRepository transactionDetailRepository;
    @Override
    public TransactionDetail saveTransaction(TransactionDetail transactionDetail) {
        return transactionDetailRepository.save(transactionDetail);
    }

    @Override
    public Page<TransactionDetailResponse> historyTransaction(Integer offset, Integer limit) {

        UserDetails userDetails = UserInfo.userInfo();
        System.out.println("cek user " + userDetails);
        Pageable pageable = PageRequest.of(offset,limit);

        Page<TransactionDetailsProjections> getHistoryByEmail = transactionDetailRepository.getDetailsTransactionHistory(userDetails.getUsername(),pageable);

        List<TransactionDetailResponse> transactionDetailResponses = getHistoryByEmail.stream().map(data -> TransactionDetailResponse.builder()
                    .invoice_number(data.getInvoiceNumber())
                    .transaction_type(data.getTransactionType())
                    .description(data.getDescription())
                    .total_amount(data.getTotalAmount())
                    .created_on(data.getCreatedOn())
                    .build()).toList();


        System.out.println("response :" + transactionDetailResponses);
        return new PageImpl<>(transactionDetailResponses,pageable,getHistoryByEmail.getTotalElements());
    }
}
