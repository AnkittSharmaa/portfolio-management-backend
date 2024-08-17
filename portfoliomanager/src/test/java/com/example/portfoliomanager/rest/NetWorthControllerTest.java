package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.NetWorthDTO;
import com.example.portfoliomanager.service.NetWorthService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NetWorthControllerTest {

    @Mock
    private NetWorthService netWorthService;

    @InjectMocks
    private NetWorthController netWorthController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(netWorthController).build();
    }

    @Test
    void testGetNetWorthChart() throws Exception {
        NetWorthDTO netWorthDTO = new NetWorthDTO();
        when(netWorthService.getNetWorthChart()).thenReturn(Arrays.asList(netWorthDTO));

        mockMvc.perform(get("/api/net-worth")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }
}
