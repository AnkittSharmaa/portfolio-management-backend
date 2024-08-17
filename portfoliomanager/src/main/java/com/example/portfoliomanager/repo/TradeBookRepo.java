package com.example.portfoliomanager.repo;

import com.example.portfoliomanager.entity.TradeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeBookRepo extends JpaRepository<TradeBook, Long> {
}
