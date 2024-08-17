package com.example.portfoliomanager.service;

import com.example.portfoliomanager.entity.FundManager;
import com.example.portfoliomanager.repo.FundManagerRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FundManagerService {

    @Autowired
    private FundManagerRepo fundManagerRepository;

    public FundManagerService() {
        System.out.println("FundManager Service default constructor");
    }

    public List<FundManager> getAllFundManagers() {
        return fundManagerRepository.findAll();
    }

    public Optional<FundManager> getFundManagerById(int id) {
        return fundManagerRepository.findById(id);
    }

    public FundManager updateFundManager(FundManager fm) {
        if (!fundManagerRepository.existsById(fm.getFundManagerid())) {
            throw new EntityNotFoundException("Cannot update " + fm.getFundManagerid() + ": DNE");
        }
        return fundManagerRepository.save(fm);
    }

}
