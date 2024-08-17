package com.example.portfoliomanager.repo;

import com.example.portfoliomanager.entity.FundManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundManagerRepo extends JpaRepository<FundManager, Integer> {
}
