package com.example.Nutech_Integration.Controller;


import com.example.Nutech_Integration.Constant.AppPath;
import com.example.Nutech_Integration.DTO.Response.CommonResponse;
import com.example.Nutech_Integration.DTO.Response.PaggingResponse;
import com.example.Nutech_Integration.DTO.Response.TransactionDetailResponse;
import com.example.Nutech_Integration.Service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.TRANSACTION)
public class TransactionDetailController {

    private final TransactionDetailService transactionDetailService;

    @GetMapping("/history")
    public ResponseEntity<?> transactionDetailPaggination(@RequestParam(name = "offset" , defaultValue = "0" )Integer offset , @RequestParam(name = "limit" , defaultValue = "3") Integer limit){

        Page<TransactionDetailResponse> transactionDetailResponses = transactionDetailService.historyTransaction(offset, limit);

        PaggingResponse paggingResponse = PaggingResponse.builder()
                .offset(offset)
                .limit(limit)
                .records(transactionDetailResponses.getContent())
                .build();

        System.out.println("cek res " + paggingResponse);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get History Berhasil")
                        .data(paggingResponse)
                        .build()
                );
    }
}
