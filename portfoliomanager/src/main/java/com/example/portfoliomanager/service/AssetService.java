package com.example.portfoliomanager.service;

import com.example.portfoliomanager.entity.Asset;
import com.example.portfoliomanager.entity.CurrentAssetHoldingDTO;
import com.example.portfoliomanager.repo.AssetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    private AssetRepo assetRepository;

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }

    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public Asset updateAsset(Long id, Asset assetDetails) {
        Asset asset = assetRepository.findById(id).orElseThrow();
        asset.setAssetName(assetDetails.getAssetName());
        asset.setAssetType(assetDetails.getAssetType());
        asset.setSymbol(assetDetails.getSymbol());
        asset.setClosingPrice(assetDetails.getClosingPrice());
        return assetRepository.save(asset);
    }

    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CurrentAssetHoldingDTO> getCurrentAssetHoldings() {
        String sql = "SELECT tb.asset_id, " +
                "SUM(CASE WHEN tb.buy_sell = 'P' THEN tb.quantity ELSE -tb.quantity END) AS current_quantity, " +
                "a.closing_price, " +
                "SUM(CASE WHEN tb.buy_sell = 'P' THEN tb.quantity ELSE -tb.quantity END) * a.closing_price AS total_value " +
                "FROM trade_book tb " +
                "JOIN assets a ON tb.asset_id = a.symbol " +
                "WHERE tb.tran_status = 'A' AND tb.tran_time <= '2024-12-12 23:59:59' " +
                "GROUP BY tb.asset_id, a.closing_price " +
                "HAVING total_value > 0";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CurrentAssetHoldingDTO.class));
    }
}
