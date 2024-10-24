package com.example.Nutech_Integration.Helper;

import com.example.Nutech_Integration.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor

public class GenerateInvoice {

// "invoice_number": "INV17082023-001",

    public static String generateInvoice(Long transactionToday){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("ddMMyyyy");
        String resultDate = today.format(formater);
        System.out.println("cek tanggal " + resultDate);

        return "INV" + resultDate + "-00" + transactionToday;
    }
}
