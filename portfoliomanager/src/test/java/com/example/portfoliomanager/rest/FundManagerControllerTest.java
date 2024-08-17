package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.FundManager;
import com.example.portfoliomanager.service.FundManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FundManagerControllerTest {

    @Mock
    private FundManagerService fundManagerService;

    @InjectMocks
    private FundManagerController fundManagerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fundManagerController).build();
    }

    @Test
    void testGetFundManagers() throws Exception {
        FundManager fundManager = new FundManager();
        when(fundManagerService.getAllFundManagers()).thenReturn(Arrays.asList(fundManager));

        mockMvc.perform(get("/api/fundmanagers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetFundManagerByIdFound() throws Exception {
        FundManager fundManager = new FundManager();
        when(fundManagerService.getFundManagerById(1)).thenReturn(fundManager);
        //when(fundManagerService.getFundManagerById(anyInt())).thenReturn(fundManager);

        mockMvc.perform(get("/api/fundmanagers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetFundManagerByIdNotFound() throws Exception {
        when(fundManagerService.getFundManagerById(1)).thenReturn(null);

        mockMvc.perform(get("/api/fundmanagers/1"))
                .andExpect(status().isNotFound());
    }
}
