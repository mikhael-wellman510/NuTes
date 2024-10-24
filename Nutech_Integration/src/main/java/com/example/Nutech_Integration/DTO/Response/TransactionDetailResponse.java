package com.example.Nutech_Integration.DTO.Response;

import com.example.Nutech_Integration.Constant.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionDetailResponse {
    private String invoice_number;
    private TransactionType transaction_type;
    private String description;
    private Integer total_amount;
    private LocalDateTime created_on;

    @Override
    public String toString() {
        return "TransactionDetailResonse{" +
                "invoice_number='" + invoice_number + '\'' +
                ", transaction_type=" + transaction_type +
                ", description='" + description + '\'' +
                ", total_amount=" + total_amount +
                ", created_on=" + created_on +
                '}';
    }
}
