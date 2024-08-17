package com.example.portfoliomanager.service;

import com.example.portfoliomanager.entity.NetWorthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetWorthService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<NetWorthDTO> getNetWorthChart() {
        String sql = "WITH daily_trades AS ( " +
                "    SELECT DATE(tran_time) AS trade_date, " +
                "    SUM(CASE WHEN buy_sell = 'P' THEN trade_amount ELSE -trade_amount END) AS total_net_trade_amount " +
                "    FROM trade_book " +
                "    WHERE tran_status = 'A' " +
                "    GROUP BY DATE(tran_time) " +
                ") " +
                "SELECT fb.balance_date AS txn_date, " +
                "    SUM(fb.balance + COALESCE(dt.total_net_trade_amount, 0)) AS net_worth " +
                "FROM fund_balance_history fb " +
                "LEFT JOIN daily_trades dt ON fb.balance_date = dt.trade_date " +
                "WHERE fb.balance_date BETWEEN '2024-01-01' AND '2024-12-31' " +
                "GROUP BY fb.balance_date " +
                "HAVING SUM(fb.balance + COALESCE(dt.total_net_trade_amount, 0)) >= 0 " +
                "ORDER BY fb.balance_date";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NetWorthDTO.class));
    }
}

