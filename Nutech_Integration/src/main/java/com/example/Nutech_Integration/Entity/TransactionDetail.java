package com.example.Nutech_Integration.Entity;


import com.example.Nutech_Integration.Constant.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_transaction_detail")
@Builder(toBuilder = true)
public class TransactionDetail {

 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
private String id;

@Column(name = "invoice_number", nullable = false, length = 100)
private String invoiceNumber;

@Enumerated(EnumType.STRING)
@Column(name = "transaction_type" , nullable = false , length = 100)
private TransactionType transactionType;

@ManyToOne
@JoinColumn(name = "transaction_id")
private Transaction transactionId;

@ManyToOne
@JoinColumn(name = "service_ppob_id")
private ServicePPOB servicePPOBId;

    @Override
    public String toString() {
        return "TransactionDetail{" +
                "id='" + id + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", transactionId=" + transactionId +
                ", serviceId=" + servicePPOBId +
                '}';
    }
}
