package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.CashFlowDTO;
import com.example.portfoliomanager.service.CashFlowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CashFlowControllerTest {

    @Mock
    private CashFlowService cashFlowService;

    @InjectMocks
    private CashFlowController cashFlowController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cashFlowController).build();
    }

    @Test
    void testGetCashFlow() throws Exception {
        CashFlowDTO cashFlowDTO = new CashFlowDTO();
        when(cashFlowService.getCashFlow()).thenReturn(Arrays.asList(cashFlowDTO));

        mockMvc.perform(get("/api/cash-flow"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }
}
