package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.FundBalanceHistory;
import com.example.portfoliomanager.service.FundBalanceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fund-balance-history")
@CrossOrigin(origins = "*" ,methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class FundBalanceHistoryController {

    @Autowired
    private FundBalanceHistoryService fundBalanceHistoryService;

    @GetMapping
    public List<FundBalanceHistory> getAllFundBalanceHistories() {
        return fundBalanceHistoryService.getAllFundBalanceHistories();
    }

    @GetMapping("/fund-manager/{fundManagerId}/balance-date/{balanceDate}")
    public ResponseEntity<FundBalanceHistory> getFundBalanceHistoryById(
            @PathVariable Integer fundManagerId,
            @PathVariable String balanceDate) {

        FundBalanceHistory.FundBalanceHistoryId id = new FundBalanceHistory.FundBalanceHistoryId(fundManagerId, balanceDate);
        Optional<FundBalanceHistory> fundBalanceHistory = fundBalanceHistoryService.getFundBalanceHistoryById(id);
        return fundBalanceHistory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FundBalanceHistory> createFundBalanceHistory(@RequestBody FundBalanceHistory fundBalanceHistory) {
        FundBalanceHistory savedFundBalanceHistory = fundBalanceHistoryService.saveFundBalanceHistory(fundBalanceHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFundBalanceHistory);
    }

    @PutMapping("/fund-manager/{fundManagerId}/balance-date/{balanceDate}")
    public ResponseEntity<FundBalanceHistory> updateFundBalanceHistory(
            @PathVariable Integer fundManagerId,
            @PathVariable String balanceDate,
            @RequestBody FundBalanceHistory fundBalanceHistory) {

        FundBalanceHistory.FundBalanceHistoryId id = new FundBalanceHistory.FundBalanceHistoryId(fundManagerId, balanceDate);
        if (fundBalanceHistoryService.getFundBalanceHistoryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        fundBalanceHistory.setFundManagerId(fundManagerId);
        fundBalanceHistory.setBalanceDate(balanceDate);
        FundBalanceHistory updatedFundBalanceHistory = fundBalanceHistoryService.saveFundBalanceHistory(fundBalanceHistory);
        return ResponseEntity.ok(updatedFundBalanceHistory);
    }

    @DeleteMapping("/fund-manager/{fundManagerId}/balance-date/{balanceDate}")
    public ResponseEntity<Void> deleteFundBalanceHistory(
            @PathVariable Integer fundManagerId,
            @PathVariable String balanceDate) {

        FundBalanceHistory.FundBalanceHistoryId id = new FundBalanceHistory.FundBalanceHistoryId(fundManagerId, balanceDate);
        if (fundBalanceHistoryService.getFundBalanceHistoryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        fundBalanceHistoryService.deleteFundBalanceHistory(id);
        return ResponseEntity.noContent().build();
    }
}
