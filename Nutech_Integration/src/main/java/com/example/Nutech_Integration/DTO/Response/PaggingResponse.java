package com.example.Nutech_Integration.DTO.Response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PaggingResponse {
    private Integer offset; // Offset
    private Integer limit;
    private List<TransactionDetailResponse> records;

    @Override
    public String toString() {
        return "PaggingResponse{" +
                "offset=" + offset +
                ", limit=" + limit +
                ", records=" + records +
                '}';
    }
}
