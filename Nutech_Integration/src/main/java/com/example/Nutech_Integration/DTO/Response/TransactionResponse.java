package com.example.Nutech_Integration.DTO.Response;


import com.example.Nutech_Integration.Constant.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionResponse {

    private String invoice_number;
    private String service_code;
    private String service_name;
    private TransactionType transaction_type;
    private Integer total_amount;
    private LocalDateTime created_on;
}
