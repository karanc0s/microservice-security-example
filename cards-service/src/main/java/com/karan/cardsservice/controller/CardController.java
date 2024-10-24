package com.karan.cardsservice.controller;

import com.karan.cardsservice.constants.CardConstants;
import com.karan.cardsservice.constants.MessagesConstants;
import com.karan.cardsservice.dto.CardDTO;
import com.karan.cardsservice.dto.CardsContactInfoDTO;
import com.karan.cardsservice.dto.DataResponseDTO;
import com.karan.cardsservice.dto.ResponseDTO;
import com.karan.cardsservice.service.ICardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
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
public class CardController {


    private final ICardService cardService;

    private final CardsContactInfoDTO cardsContactInfoDTO;

    private final Environment environment;

    @Value("${build.version}")
    private String buildVersion;

    public CardController(
            ICardService cardService,
            CardsContactInfoDTO cardsContactInfoDTO,
            Environment environment
    ) {
        this.cardService = cardService;
        this.cardsContactInfoDTO = cardsContactInfoDTO;
        this.environment = environment;
    }

    @PostMapping("/create")
    public ResponseEntity<DataResponseDTO<CardDTO>> createCard(
            @Pattern(
                    regexp = "(^$|[0-9]{10})",
                    message = MessagesConstants.ERR_MSG_INVALID_MOBILE_NUMBER
            )
            @RequestParam
            String mobileNumber
    ){
        var dto = cardService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new DataResponseDTO<CardDTO>(CardConstants.STATUS_201 , CardConstants.MESSAGE_201 , dto));
    }

    @GetMapping("/fetch")
    public ResponseEntity<DataResponseDTO<CardDTO>> fetchCard(
            @Pattern(
                    regexp = "(^$|[0-9]{10})",
                    message = MessagesConstants.ERR_MSG_INVALID_MOBILE_NUMBER
            )
            @RequestParam
            String mobileNumber
    ){
        var dto = cardService.fetchCard(mobileNumber);
        return ResponseEntity.ok(
                DataResponseDTO.<CardDTO>builder()
                        .statusCode(CardConstants.STATUS_201)
                        .message(CardConstants.MESSAGE_201)
                        .data(dto)
                        .build()
        );
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCard(
            @Valid
            @RequestBody
            CardDTO cardDTO
    ){
        boolean isUpdated = cardService.updateCard(cardDTO);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCard(
            @Pattern(
                    regexp = "(^$|[0-9]{10})",
                    message = MessagesConstants.ERR_MSG_INVALID_MOBILE_NUMBER
            )
            @RequestParam
            String mobileNumber
    ){
        boolean isDeleted = cardService.deleteCard(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
        }
    }


    @GetMapping("build-info")
    public ResponseEntity<String> getBuildVersion(){
        return ResponseEntity.ok(buildVersion);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDTO> getContactInfo(){
        return ResponseEntity.ok(cardsContactInfoDTO);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.ok(
                environment.getProperty("JAVA_HOME")
        );
    }





}
