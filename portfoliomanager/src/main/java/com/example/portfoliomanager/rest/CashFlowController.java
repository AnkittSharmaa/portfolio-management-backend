package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.CashFlowDTO;
import com.example.portfoliomanager.service.CashFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cash-flow")
@CrossOrigin(origins = "*" ,methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class CashFlowController {

    @Autowired
    private CashFlowService cashFlowService;

    @GetMapping
    public ResponseEntity<List<CashFlowDTO>> getCashFlow() {
        List<CashFlowDTO> cashFlow = cashFlowService.getCashFlow();
        return ResponseEntity.ok(cashFlow);
    }
}

