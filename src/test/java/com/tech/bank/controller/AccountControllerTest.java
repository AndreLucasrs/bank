package com.tech.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.bank.config.ControllerExceptionHandler;
import com.tech.bank.dto.AccountDTO;
import com.tech.bank.dto.NewAccountDTO;
import com.tech.bank.exception.ResourceNotFoundException;
import com.tech.bank.model.Account;
import com.tech.bank.service.CreateNewAccount;
import com.tech.bank.service.FindAccount;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FindAccount findAccount;

    @Mock
    private CreateNewAccount createNewAccount;

    @InjectMocks
    private AccountController accountController;

    private JacksonTester<NewAccountDTO> jsonNewAccount;

    private JacksonTester<AccountDTO> jsonAccount;

    @BeforeEach
    public void setUp() {

        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("should find an account")
    public void findAccount() throws Exception {

        given(findAccount.findById(1L))
                .willReturn(new AccountDTO(getAccount()));

        MockHttpServletResponse response = mockMvc.perform(
                        get("/accounts/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonAccount.write(new AccountDTO(getAccount())).getJson()
        );
    }

    @Test
    @DisplayName("shouldn't find an account")
    public void notFoundAccount() throws Exception {

        given(findAccount.findById(1L))
                .willThrow(new ResourceNotFoundException("Account not found"));

        MockHttpServletResponse response = mockMvc.perform(
                        get("/accounts/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("should create a new account")
    public void createNewAccount() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                post("/accounts/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonNewAccount.write(new NewAccountDTO("12345678900")).getJson()
                )).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    private Account getAccount() {
        return new Account(1L, "12345678900");
    }
}