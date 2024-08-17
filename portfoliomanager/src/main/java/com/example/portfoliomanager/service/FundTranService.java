package com.example.portfoliomanager.service;

import com.example.portfoliomanager.entity.FundBalanceHistory;
import com.example.portfoliomanager.entity.FundManager;
import com.example.portfoliomanager.entity.FundTran;
import com.example.portfoliomanager.repo.FundBalanceHistoryRepo;
import com.example.portfoliomanager.repo.FundManagerRepo;
import com.example.portfoliomanager.repo.FundTranRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FundTranService {

    @Autowired
    private FundTranRepo fundTranRepository;

    @Autowired
    private FundManagerRepo fundManagerRepository;

    @Autowired
    private FundBalanceHistoryRepo fundBalanceHistoryRepository;

    public List<FundTran> getAllFundTrans() {
        return fundTranRepository.findAll();
    }

    public FundTran getFundTranById(Integer id) {
        return fundTranRepository.findById(id).orElse(null);
    }

    public FundTran saveFundTran(FundTran fundTran) {
        return fundTranRepository.save(fundTran);
    }

    public void deleteFundTran(Integer id) {
        fundTranRepository.deleteById(id);
    }

    @Transactional
    public void settleTransaction(FundTran fundTran) {
        // Update fund manager table
        FundManager fundManager = fundManagerRepository.findById(fundTran.getFundManagerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Fund Manager ID"));
        fundManager.setCurrentBalance(fundTran.getFundAccBalance());
        fundManager.setDate(fundTran.getTranTime());
        fundManagerRepository.save(fundManager);

        // update fund balance history
        FundBalanceHistory fbh = new FundBalanceHistory();
        fbh.setFundManagerId(fundTran.getFundManagerId());
        fbh.setBalanceDate(fundTran.getTranTime());
        fbh.setBalance(fundTran.getFundAccBalance());
        fundBalanceHistoryRepository.save(fbh);
    }
}
