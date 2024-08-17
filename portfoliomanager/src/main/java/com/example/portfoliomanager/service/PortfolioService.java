package com.example.portfoliomanager.service;

import com.example.portfoliomanager.entity.Portfolio;
import com.example.portfoliomanager.repo.PortfolioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepo portfolioRepository;

    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    public Optional<Portfolio> getPortfolioById(Long id) {
        return portfolioRepository.findById(id);
    }

    public Portfolio savePortfolio(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    public Portfolio updatePortfolio(Long id, Portfolio portfolioDetails) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow();
        portfolio.setFundManager(portfolioDetails.getFundManager());
        portfolio.setAsset(portfolioDetails.getAsset());
        portfolio.setQuantity(portfolioDetails.getQuantity());
        portfolio.setTotalValue(portfolioDetails.getTotalValue());
        return portfolioRepository.save(portfolio);
    }

    public void deletePortfolio(Long id) {
        portfolioRepository.deleteById(id);
    }
}
