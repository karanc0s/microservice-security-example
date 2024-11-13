package com.karan.loansservice;


import com.karan.loansservice.service.ILoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class LoansServiceApplicationTests {



    @Test
    void createLoanTest(){
        assertEquals(12,122);

    }

}
