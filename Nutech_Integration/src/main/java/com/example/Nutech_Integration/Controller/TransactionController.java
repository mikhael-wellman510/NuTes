package com.example.Nutech_Integration.Controller;


import com.example.Nutech_Integration.Constant.AppPath;
import com.example.Nutech_Integration.DTO.Request.TransactionRequest;
import com.example.Nutech_Integration.DTO.Response.BalanceResponse;
import com.example.Nutech_Integration.DTO.Response.CommonResponse;
import com.example.Nutech_Integration.DTO.Response.TransactionResponse;
import com.example.Nutech_Integration.Service.ServicePPOBService;
import com.example.Nutech_Integration.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(){
        BalanceResponse balance = transactionService.getBalance();


        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<BalanceResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sukses")
                        .data(balance)
                        .build()
                );

    }

    @PostMapping("/topup")
    public ResponseEntity<?> topUp(@RequestBody TransactionRequest transactionRequest){

        BalanceResponse topUpService = transactionService.topUp(transactionRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<BalanceResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Top Up Balance Berhasil")
                        .data(topUpService)
                        .build()
                );
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> transaction(@RequestBody TransactionRequest transactionRequest){
        TransactionResponse transaction =  transactionService.transaction(transactionRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<TransactionResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Transaksi Berhasil")
                        .data(transaction)
                        .build()
                );
    }

}
