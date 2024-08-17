package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.FundTran;
import com.example.portfoliomanager.service.FundTranService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FundTranControllerTest {

    @Mock
    private FundTranService fundTranService;

    @InjectMocks
    private FundTranController fundTranController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fundTranController).build();
    }

    @Test
    void testGetAllFundTrans() throws Exception {
        FundTran fundTran = new FundTran();
        when(fundTranService.getAllFundTrans()).thenReturn(Arrays.asList(fundTran));

        mockMvc.perform(get("/api/funds"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetFundTranByIdFound() throws Exception {
        FundTran fundTran = new FundTran();
        when(fundTranService.getFundTranById(anyInt())).thenReturn(fundTran);

        mockMvc.perform(get("/api/funds/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetFundTranByIdNotFound() throws Exception {
        when(fundTranService.getFundTranById(anyInt())).thenReturn(null);

        mockMvc.perform(get("/api/funds/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateFundTran() throws Exception {
        FundTran fundTran = new FundTran();
        when(fundTranService.saveFundTran(any(FundTran.class))).thenReturn(fundTran);

        mockMvc.perform(post("/api/funds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recId\": 1, \"fundName\":\"Test Fund\"}")) // Replace with actual fields of FundTran
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testUpdateFundTranFound() throws Exception {
        FundTran fundTran = new FundTran();
        when(fundTranService.getFundTranById(anyInt())).thenReturn(fundTran);
        when(fundTranService.saveFundTran(any(FundTran.class))).thenReturn(fundTran);

        mockMvc.perform(put("/api/funds/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recId\": 1, \"fundName\":\"Updated Fund\"}")) // Replace with actual fields of FundTran
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testUpdateFundTranNotFound() throws Exception {
        when(fundTranService.getFundTranById(anyInt())).thenReturn(null);

        mockMvc.perform(put("/api/funds/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recId\": 1, \"fundName\":\"Updated Fund\"}")) // Replace with actual fields of FundTran
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteFundTran() throws Exception {
        mockMvc.perform(delete("/api/funds/1"))
                .andExpect(status().isOk());
    }
}
