package com.karan.accountsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DataResponseDTO<T> {

    private String statusCode;

    private String message;

    private T data;

}
