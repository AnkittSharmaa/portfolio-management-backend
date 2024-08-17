package com.example.portfoliomanager.service;

import com.example.portfoliomanager.entity.FundBalanceHistory;
import com.example.portfoliomanager.repo.FundBalanceHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FundBalanceHistoryService {

    @Autowired
    private FundBalanceHistoryRepo fundBalanceHistoryRepo;

    public List<FundBalanceHistory> getAllFundBalanceHistories() {
        return fundBalanceHistoryRepo.findAll();
    }

    public Optional<FundBalanceHistory> getFundBalanceHistoryById(FundBalanceHistory.FundBalanceHistoryId id) {
        return fundBalanceHistoryRepo.findById(id);
    }

    public FundBalanceHistory saveFundBalanceHistory(FundBalanceHistory fundBalanceHistory) {
        return fundBalanceHistoryRepo.save(fundBalanceHistory);
    }

    public void deleteFundBalanceHistory(FundBalanceHistory.FundBalanceHistoryId id) {
        fundBalanceHistoryRepo.deleteById(id);
    }
}
