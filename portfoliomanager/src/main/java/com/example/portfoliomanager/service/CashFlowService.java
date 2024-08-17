package com.example.portfoliomanager.service;

import com.example.portfoliomanager.entity.CashFlowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashFlowService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CashFlowDTO> getCashFlow() {
        String sql = "SELECT tran_type AS label, SUM(amount) AS value " +
                "FROM funds " +
                "GROUP BY tran_type";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CashFlowDTO.class));
    }
}

