package com.example.portfoliomanager.repo;

import com.example.portfoliomanager.entity.FundBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundBalanceHistoryRepo extends JpaRepository<FundBalanceHistory, FundBalanceHistory.FundBalanceHistoryId> {
}
