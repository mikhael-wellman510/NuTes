package com.example.Nutech_Integration.Service.Impl;

import com.example.Nutech_Integration.DTO.Response.BalanceResponse;
import com.example.Nutech_Integration.Helper.UserInfo;
import com.example.Nutech_Integration.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Override
    public BalanceResponse getBalance() {
        UserDetails userDetails = UserInfo.userInfo();

        System.out.println("Details user : " + userDetails);

        // Tambahkan di akun auto tambah 1000000
        // Cek Berapa Saldo nya
        return null;
    }
}
