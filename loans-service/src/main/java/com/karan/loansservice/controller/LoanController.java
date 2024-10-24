package com.karan.loansservice.controller;

import com.karan.loansservice.constants.LoanConstants;
import com.karan.loansservice.constants.MessagesConstants;
import com.karan.loansservice.dto.ApiResponseDTO;
import com.karan.loansservice.dto.LoanContactInfoDTO;
import com.karan.loansservice.dto.LoanDTO;
import com.karan.loansservice.dto.ResponseDTO;
import com.karan.loansservice.service.ILoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoanController {


    private final ILoanService loanService;
    private final Environment environment;
    private final LoanContactInfoDTO contactInfo;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    public LoanController(
            ILoanService loanService,
            Environment environment,
            LoanContactInfoDTO contactInfo
    ) {
        this.loanService = loanService;
        this.environment = environment;
        this.contactInfo = contactInfo;
    }




    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<LoanDTO>> createLoan(
            @Pattern(
                    regexp = "(^$|[0-9]{10})",
                    message = MessagesConstants.ERR_MSG_INVALID_MOBILE_NUMBER
            )
            @RequestParam
            String mobileNumber
    ) {
        LoanDTO loanDTO = loanService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponseDTO.<LoanDTO>builder()
                                .statusCode(LoanConstants.STATUS_201)
                                .message(LoanConstants.MESSAGE_201)
                                .data(loanDTO)
                                .build()
                );
    }

    @GetMapping("/fetch")
    public ResponseEntity<ApiResponseDTO<LoanDTO>> fetchLoan(
            @RequestParam
            @Pattern(
                    regexp = "(^$|[0-9]{10})",
                    message = MessagesConstants.ERR_MSG_INVALID_MOBILE_NUMBER
            )
            String mobileNumber
    ) {
        LoanDTO loanDTO = loanService.fetchLoan(mobileNumber);
        return ResponseEntity.ok(
                ApiResponseDTO.<LoanDTO>builder()
                        .statusCode(LoanConstants.STATUS_200)
                        .message(LoanConstants.MESSAGE_200)
                        .data(loanDTO)
                        .build()
        );
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoan(
            @RequestBody
            @Valid
            LoanDTO loanDTO
    ) {
        boolean isUpdated = loanService.updateLoan(loanDTO);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoan(
            @RequestParam
            @Pattern(
                    regexp = "(^$|[0-9]{10})",
                    message = MessagesConstants.ERR_MSG_INVALID_MOBILE_NUMBER
            )
            String mobileNumber
    ) {
        boolean isDeleted = loanService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));

        }else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));

        }
    }



    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.ok(buildVersion);
    }


    @GetMapping("/contact-info")
    public ResponseEntity<LoanContactInfoDTO> getContactInfo(){
        return ResponseEntity.ok(contactInfo);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.ok(
                environment.getProperty("JAVA_HOME")
        );
    }
}
