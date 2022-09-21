package com.tech.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.bank.config.ControllerExceptionHandler;
import com.tech.bank.dto.TransacationDTO;
import com.tech.bank.model.OperationType;
import com.tech.bank.service.CreateTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateTransaction createTransaction;

    @InjectMocks
    private TransactionController transactionController;

    private JacksonTester<TransacationDTO> jsonTransaction;

    @BeforeEach
    public void setUp() {

        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("should create a transaction")
    public void createTransaction() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                post("/transactions/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonTransaction.write(new TransacationDTO(1L, OperationType.PAYMENT, BigDecimal.TEN)).getJson()
                )).andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

}