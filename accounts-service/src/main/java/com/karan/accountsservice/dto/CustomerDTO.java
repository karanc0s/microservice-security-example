package com.karan.accountsservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {


    @NotEmpty(message = "Name cannot be null or empty")
    @Size(
            min = 5,
            max = 30,
            message = "The Length of the customer name should be between 5 and 30"
    )
    private String name;


    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email address should be valid value")
    private String email;


    @Pattern(
            regexp = "(^$|[0-9]{10})",
            message = "Mobile number must be 10 digits"
    )
    @NotEmpty(message = "Mobile cannot be empty")
    private String mobileNumber;

    private AccountsDTO accountsDTO;
}
