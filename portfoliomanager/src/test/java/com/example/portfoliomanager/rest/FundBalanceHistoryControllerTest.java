package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.FundBalanceHistory;
import com.example.portfoliomanager.service.FundBalanceHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FundBalanceHistoryControllerTest {

    @Mock
    private FundBalanceHistoryService fundBalanceHistoryService;

    @InjectMocks
    private FundBalanceHistoryController fundBalanceHistoryController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fundBalanceHistoryController).build();
    }

    @Test
    void testGetAllFundBalanceHistories() throws Exception {
        FundBalanceHistory fundBalanceHistory = new FundBalanceHistory();
        when(fundBalanceHistoryService.getAllFundBalanceHistories()).thenReturn(Arrays.asList(fundBalanceHistory));

        mockMvc.perform(get("/api/fund-balance-history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetFundBalanceHistoryByIdFound() throws Exception {
        FundBalanceHistory.FundBalanceHistoryId id = new FundBalanceHistory.FundBalanceHistoryId(1, Date.valueOf("2024-01-01"));
        FundBalanceHistory fundBalanceHistory = new FundBalanceHistory();
        when(fundBalanceHistoryService.getFundBalanceHistoryById(id)).thenReturn(Optional.of(fundBalanceHistory));

        mockMvc.perform(get("/api/fund-balance-history/fund-manager/1/balance-date/2024-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetFundBalanceHistoryByIdNotFound() throws Exception {
        FundBalanceHistory.FundBalanceHistoryId id = new FundBalanceHistory.FundBalanceHistoryId(1, Date.valueOf("2024-01-01"));
        when(fundBalanceHistoryService.getFundBalanceHistoryById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/fund-balance-history/fund-manager/1/balance-date/2024-01-01"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateFundBalanceHistory() throws Exception {
        FundBalanceHistory fundBalanceHistory = new FundBalanceHistory();
        when(fundBalanceHistoryService.saveFundBalanceHistory(fundBalanceHistory)).thenReturn(fundBalanceHistory);

        mockMvc.perform(post("/api/fund-balance-history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testUpdateFundBalanceHistoryFound() throws Exception {
        FundBalanceHistory.FundBalanceHistoryId id = new FundBalanceHistory.FundBalanceHistoryId(1, Date.valueOf("2024-01-01"));
        FundBalanceHistory fundBalanceHistory = new FundBalanceHistory();
        when(fundBalanceHistoryService.getFundBalanceHistoryById(id)).thenReturn(Optional.of(fundBalanceHistory));
        when(fundBalanceHistoryService.saveFundBalanceHistory(fundBalanceHistory)).thenReturn(fundBalanceHistory);

        mockMvc.perform(put("/api/fund-balance-history/fund-manager/1/balance-date/2024-01-01")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                //.andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testUpdateFundBalanceHistoryNotFound() throws Exception {
        FundBalanceHistory.FundBalanceHistoryId id = new FundBalanceHistory.FundBalanceHistoryId(1, Date.valueOf("2024-01-01"));
        when(fundBalanceHistoryService.getFundBalanceHistoryById(id)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/fund-balance-history/fund-manager/1/balance-date/2024-01-01")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteFundBalanceHistoryFound() throws Exception {
        FundBalanceHistory.FundBalanceHistoryId id = new FundBalanceHistory.FundBalanceHistoryId(1, Date.valueOf("2024-01-01"));
        FundBalanceHistory fundBalanceHistory = new FundBalanceHistory();
        when(fundBalanceHistoryService.getFundBalanceHistoryById(id)).thenReturn(Optional.of(fundBalanceHistory));

        mockMvc.perform(delete("/api/fund-balance-history/fund-manager/1/balance-date/2024-01-01"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteFundBalanceHistoryNotFound() throws Exception {
        FundBalanceHistory.FundBalanceHistoryId id = new FundBalanceHistory.FundBalanceHistoryId(1, Date.valueOf("2024-01-01"));
        when(fundBalanceHistoryService.getFundBalanceHistoryById(id)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/fund-balance-history/fund-manager/1/balance-date/2024-01-01"))
                .andExpect(status().isNotFound());
    }
}
