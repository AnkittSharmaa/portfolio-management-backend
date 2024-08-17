package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.NetWorthDTO;
import com.example.portfoliomanager.service.NetWorthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/net-worth")
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class NetWorthController {

    @Autowired
    private NetWorthService netWorthService;

    @GetMapping
    public ResponseEntity<List<NetWorthDTO>> getNetWorthChart() {
        List<NetWorthDTO> netWorthChart = netWorthService.getNetWorthChart();
        return ResponseEntity.ok(netWorthChart);
    }
}

