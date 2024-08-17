package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.FundManager;
import com.example.portfoliomanager.service.FundManagerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fundmanagers")
@CrossOrigin(origins = "*" ,methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class FundManagerController {

    @Autowired
    private FundManagerService fundManagerService;

    public FundManagerController() {
        System.out.println("Fund Manager controller default constructor");
    }

    @GetMapping
    public List<FundManager> getFundManagers() {
        return fundManagerService.getAllFundManagers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FundManager> getFundManagerById(@PathVariable int id) {
        return fundManagerService.getFundManagerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Object> updateFundManager(@RequestBody FundManager fm) {
        try {
            FundManager tmp = fundManagerService.updateFundManager(fm);
            return ResponseEntity.ok(tmp);
        } catch (EntityNotFoundException e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorMap);
        }
    }

}
