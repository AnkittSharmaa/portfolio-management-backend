package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.Portfolio;
import com.example.portfoliomanager.service.PortfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PortfolioControllerTest {

    @Mock
    private PortfolioService portfolioService;

    @InjectMocks
    private PortfolioController portfolioController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(portfolioController).build();
    }

    @Test
    void testGetAllPortfolios() throws Exception {
        Portfolio portfolio = new Portfolio();
        when(portfolioService.getAllPortfolios()).thenReturn(Arrays.asList(portfolio));

        mockMvc.perform(get("/api/portfolios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetPortfolioByIdFound() throws Exception {
        Portfolio portfolio = new Portfolio();
        when(portfolioService.getPortfolioById(anyLong())).thenReturn(Optional.of(portfolio));

        mockMvc.perform(get("/api/portfolios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetPortfolioByIdNotFound() throws Exception {
        when(portfolioService.getPortfolioById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/portfolios/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePortfolio() throws Exception {
        Portfolio portfolio = new Portfolio();
        when(portfolioService.savePortfolio(any(Portfolio.class))).thenReturn(portfolio);

        mockMvc.perform(post("/api/portfolios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Portfolio\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testUpdatePortfolio() throws Exception {
        Portfolio portfolio = new Portfolio();
        when(portfolioService.updatePortfolio(anyLong(), any(Portfolio.class))).thenReturn(portfolio);

        mockMvc.perform(put("/api/portfolios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Portfolio\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testDeletePortfolio() throws Exception {
        mockMvc.perform(delete("/api/portfolios/1"))
                .andExpect(status().isNoContent());
    }
}
