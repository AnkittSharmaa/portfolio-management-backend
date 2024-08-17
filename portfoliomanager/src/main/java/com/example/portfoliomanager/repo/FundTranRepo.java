package com.example.portfoliomanager.repo;

import com.example.portfoliomanager.entity.FundTran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundTranRepo extends JpaRepository<FundTran, Integer> {
}
