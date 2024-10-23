package com.example.Nutech_Integration.DTO.Response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BalanceResponse {
    private Integer balance;

    @Override
    public String toString() {
        return "BalanceResponse{" +
                "balance=" + balance +
                '}';
    }
}
