package com.example.Nutech_Integration.Controller;


import com.example.Nutech_Integration.Constant.AppPath;
import com.example.Nutech_Integration.DTO.Response.BalanceResponse;
import com.example.Nutech_Integration.DTO.Response.CommonResponse;
import com.example.Nutech_Integration.Service.ServicePPOBService;
import com.example.Nutech_Integration.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
