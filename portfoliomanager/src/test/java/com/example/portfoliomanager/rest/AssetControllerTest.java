package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.Asset;
import com.example.portfoliomanager.entity.CurrentAssetHoldingDTO;
import com.example.portfoliomanager.service.AssetService;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AssetControllerTest {

    @Mock
    private AssetService assetService;

    @InjectMocks
    private AssetController assetController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(assetController).build();
    }

    @Test
    void testGetAllAssets() throws Exception {
        Asset asset = new Asset();
        when(assetService.getAllAssets()).thenReturn(Arrays.asList(asset));

        mockMvc.perform(get("/api/assets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetAssetByIdFound() throws Exception {
        Asset asset = new Asset();
        when(assetService.getAssetById(1L)).thenReturn(Optional.of(asset));

        mockMvc.perform(get("/api/assets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetAssetByIdNotFound() throws Exception {
        when(assetService.getAssetById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/assets/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetCurrentAssetHoldings() throws Exception {
        CurrentAssetHoldingDTO holding = new CurrentAssetHoldingDTO();
        when(assetService.getCurrentAssetHoldings()).thenReturn(Arrays.asList(holding));

        mockMvc.perform(get("/api/assets/current-holdings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }
}