package com.example.Nutech_Integration.ErrorHandling;

import com.example.Nutech_Integration.DTO.Response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandlre {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {

        CommonResponse commonResponse = CommonResponse.builder()
                .statusCode(102)
                .message(e.getMessage())
                .data(null)
                .build();
        return ResponseEntity.badRequest().body(commonResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<CommonResponse<Map<String,String>>> handleValidationException(MethodArgumentNotValidException ex , WebRequest request){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        CommonResponse<Map<String, String>> response = CommonResponse.<Map<String, String>>builder()
                .statusCode(HttpStatus.PROCESSING.value())
                .message(errors.get("email"))
                .data(null)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CommonResponse<String>> handleBadCredentialsException(BadCredentialsException ex) {
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(103)
                .message("Username atau Password salah")
                .data(null) // Tidak ada data tambahan
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

}
