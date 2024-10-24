package com.karan.loansservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ApiResponseDTO<T> {

    private String statusCode;

    private String message;

    private T data;

}
