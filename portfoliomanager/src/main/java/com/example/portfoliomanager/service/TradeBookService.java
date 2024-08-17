package com.example.portfoliomanager.service;

import com.example.portfoliomanager.entity.FundManager;
import com.example.portfoliomanager.entity.TradeBook;
import com.example.portfoliomanager.repo.FundManagerRepo;
import com.example.portfoliomanager.repo.TradeBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeBookService {

    @Autowired
    private TradeBookRepo tradeBookRepo;
    @Autowired
    private FundManagerRepo fundManagerRepo; // Assuming you have this repository



    public List<TradeBook> getAllTrades() {
        return tradeBookRepo.findAll();
    }

    public Optional<TradeBook> getTradeById(Long id) {
        return tradeBookRepo.findById(id);
    }

    public TradeBook saveTrade(TradeBook tradeBook) {
        // Save the trade
        TradeBook savedTrade = tradeBookRepo.save(tradeBook);

        // Fetch the fund manager associated with the trade
        FundManager fundManager = tradeBook.getFundManager();

        // Calculate new balance
        double newBalance = fundManager.getCurrentBalance();
        if (tradeBook.getBuySell().equals("B")) { // For buy trades
            newBalance -= tradeBook.getTradeAmount();
        } else { // For sell trades
            newBalance += tradeBook.getTradeAmount();
        }

        // Update fund manager's balance
        fundManager.setCurrentBalance(newBalance);
        fundManagerRepo.save(fundManager);

        return savedTrade;
    }

    public TradeBook updateTrade(Long id, TradeBook tradeBookDetails) {
        TradeBook tradeBook = tradeBookRepo.findById(id).orElseThrow();
        tradeBook.setAsset(tradeBookDetails.getAsset());
        tradeBook.setAssetPrice(tradeBookDetails.getAssetPrice());
        tradeBook.setQuantity(tradeBookDetails.getQuantity());
        tradeBook.setTradeAmount(tradeBookDetails.getTradeAmount());
        tradeBook.setBuySell(tradeBookDetails.getBuySell());
        tradeBook.setTranTime(tradeBookDetails.getTranTime());
        tradeBook.setFundManager(tradeBookDetails.getFundManager());
        tradeBook.setTranStatus(tradeBookDetails.getTranStatus());
        return tradeBookRepo.save(tradeBook);
    }

    public void deleteTrade(Long id) {
        tradeBookRepo.deleteById(id);
    }
}
