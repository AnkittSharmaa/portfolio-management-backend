package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.Asset;
import com.example.portfoliomanager.entity.CurrentAssetHoldingDTO;
import com.example.portfoliomanager.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@CrossOrigin(origins = "*" ,methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/current-holdings")
    public ResponseEntity<List<CurrentAssetHoldingDTO>> getCurrentAssetHoldings() {
        List<CurrentAssetHoldingDTO> holdings = assetService.getCurrentAssetHoldings();
        return ResponseEntity.ok(holdings);
    }

}
