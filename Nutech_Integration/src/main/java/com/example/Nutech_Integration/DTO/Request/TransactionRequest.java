package com.example.Nutech_Integration.DTO.Request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionRequest {
    Integer top_up_amount;
    String service_code;

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "top_up_amount=" + top_up_amount +
                ", service_code='" + service_code + '\'' +
                '}';
    }
}
