package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.TradeBook;
import com.example.portfoliomanager.service.TradeBookService;
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

class TradeBookControllerTest {

    @Mock
    private TradeBookService tradeBookService;

    @InjectMocks
    private TradeBookController tradeBookController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tradeBookController).build();
    }

    @Test
    void testGetAllTrades() throws Exception {
        TradeBook tradeBook = new TradeBook();
        when(tradeBookService.getAllTrades()).thenReturn(Arrays.asList(tradeBook));

        mockMvc.perform(get("/api/trades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetTradeByIdFound() throws Exception {
        TradeBook tradeBook = new TradeBook();
        when(tradeBookService.getTradeById(anyLong())).thenReturn(Optional.of(tradeBook));

        mockMvc.perform(get("/api/trades/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetTradeByIdNotFound() throws Exception {
        when(tradeBookService.getTradeById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/trades/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateTrade() throws Exception {
        TradeBook tradeBook = new TradeBook();
        when(tradeBookService.saveTrade(any(TradeBook.class))).thenReturn(tradeBook);

        mockMvc.perform(post("/api/trades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Trade\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testUpdateTrade() throws Exception {
        TradeBook tradeBook = new TradeBook();
        when(tradeBookService.updateTrade(anyLong(), any(TradeBook.class))).thenReturn(tradeBook);

        mockMvc.perform(put("/api/trades/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Trade\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testDeleteTrade() throws Exception {
        mockMvc.perform(delete("/api/trades/1"))
                .andExpect(status().isNoContent());
    }
}
