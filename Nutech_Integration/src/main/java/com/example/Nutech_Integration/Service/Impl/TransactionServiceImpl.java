package com.example.Nutech_Integration.Service.Impl;

import com.example.Nutech_Integration.Constant.TransactionType;
import com.example.Nutech_Integration.DTO.Request.TransactionRequest;
import com.example.Nutech_Integration.DTO.Response.BalanceResponse;
import com.example.Nutech_Integration.DTO.Response.TransactionResponse;
import com.example.Nutech_Integration.Entity.Auth.User;
import com.example.Nutech_Integration.Entity.ServicePPOB;
import com.example.Nutech_Integration.Entity.Transaction;
import com.example.Nutech_Integration.Entity.TransactionDetail;
import com.example.Nutech_Integration.ErrorHandling.InsufficientBalanceException;
import com.example.Nutech_Integration.Helper.GenerateInvoice;
import com.example.Nutech_Integration.Helper.UserInfo;
import com.example.Nutech_Integration.InterfaceQuery.UserProfile;
import com.example.Nutech_Integration.Repository.Auth.UserRepository;
import com.example.Nutech_Integration.Repository.ServicePPOBRepository;
import com.example.Nutech_Integration.Repository.TransactionRepository;
import com.example.Nutech_Integration.Service.Auth.UserService;
import com.example.Nutech_Integration.Service.ServicePPOBService;
import com.example.Nutech_Integration.Service.TransactionDetailService;
import com.example.Nutech_Integration.Service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final ServicePPOBService servicePPOBService;
    private final TransactionDetailService transactionDetailService;
    @Override
    public BalanceResponse getBalance() {
        UserDetails userDetails = UserInfo.userInfo();

        UserProfile getUser = transactionRepository.findByEmail(userDetails.getUsername());
        System.out.println(getUser.getId());
        System.out.println(getUser.getEmail());
        return BalanceResponse.builder()
                .balance(getUser.getBalance())
                .build();
    }

    @Override
    @Transactional
    public BalanceResponse topUp(TransactionRequest transactionRequest) {
        UserDetails userDetails = UserInfo.userInfo();
        UserProfile getCurrentDataUser = transactionRepository.findByEmail(userDetails.getUsername());
        User getUserData = userService.findUserById(getCurrentDataUser.getId());
        ServicePPOB servicePPOB = servicePPOBService.findServicePPOBbyServiceCode("TOP_UP");

        if (getUserData != null && transactionRequest.getTop_up_amount() > 0){

            Transaction addTransaction = Transaction.builder()
                    .userId(getUserData)
                    .createdOn(LocalDateTime.now())
                    .build();

            Transaction transaction =  transactionRepository.save(addTransaction);

            Long countTransaction = transactionRepository.count();
            String invoice = GenerateInvoice.generateInvoice(countTransaction);
            TransactionDetail transactionDetail = TransactionDetail.builder()
                    .invoiceNumber(invoice)
                    .transactionType(TransactionType.TOPUP)
                    .transactionId(transaction)
                    .servicePPOBId(servicePPOB)
                    .amount(transactionRequest.getTop_up_amount())
                    .build();

            TransactionDetail saveTransaction =  transactionDetailService.saveTransaction(transactionDetail);
            getUserData.setBalance(getUserData.getBalance() + transactionRequest.getTop_up_amount());
            User saveUser = userService.saveUser(getUserData);
            return BalanceResponse.builder()
                    .balance(saveUser.getBalance())
                    .build();
        }else {
           throw new HttpMessageNotReadableException("Parameter amount hanya boleh angka dan tidak boleh lebih kecil dari 0");
        }


    }

    @Override
    @Transactional
    public TransactionResponse transaction(TransactionRequest transactionRequest) {
        UserDetails userDetails = UserInfo.userInfo();
        UserProfile getCurrentUser = transactionRepository.findByEmail(userDetails.getUsername());
        User user = userService.findUserById(getCurrentUser.getId());


        ServicePPOB servicePPOB = servicePPOBService.findServicePPOBbyServiceCode(transactionRequest.getService_code());

        if (transactionRequest.getService_code().equals("TOP_UP")){
            throw new IllegalArgumentException("Untuk top up gunakan menu TOP UP");
        }

        if (servicePPOB != null){


            if (user.getBalance() <= servicePPOB.getServiceTarif() || user.getBalance() < 0){
                throw new InsufficientBalanceException("Saldo Tidak Cukup");
            }


            Transaction transaction = Transaction.builder()
                    .userId(user)
                    .createdOn(LocalDateTime.now())
                    .build();

            Transaction saveTransaction = transactionRepository.save(transaction);

            Long countTransaction = transactionRepository.count();
            String invoice = GenerateInvoice.generateInvoice(countTransaction);

            TransactionDetail transactionDetail = TransactionDetail.builder()
                    .invoiceNumber(invoice)
                    .transactionType(TransactionType.PAYMENT)
                    .servicePPOBId(servicePPOB)
                    .transactionId(saveTransaction)
                    .amount(servicePPOB.getServiceTarif())
                    .build();

            TransactionDetail saveTransactionDetail = transactionDetailService.saveTransaction(transactionDetail);

            if (saveTransactionDetail != null){
                user.setBalance(user.getBalance() - servicePPOB.getServiceTarif());

                User updateBalanceUser = userService.saveUser(user);
                System.out.println(updateBalanceUser);
            }else {
                throw new IllegalArgumentException("Transaction Failed");
            }

            return TransactionResponse.builder()
                    .invoice_number(invoice)
                    .service_code(servicePPOB.getServiceCode())
                    .service_name(servicePPOB.getServiceName())
                    .transaction_type(saveTransactionDetail.getTransactionType())
                    .total_amount(servicePPOB.getServiceTarif())
                    .created_on(saveTransaction.getCreatedOn())
                    .build();
        }else {
            throw new IllegalArgumentException("Service atau layanan tidak ditemukan");
        }

    }
}
