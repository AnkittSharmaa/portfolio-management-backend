package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.TradeBook;
import com.example.portfoliomanager.service.TradeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trades")
@CrossOrigin(origins = "*" ,methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class TradeBookController {

    @Autowired
    private TradeBookService tradeBookService;

    @GetMapping
    public List<TradeBook> getAllTrades() {
        return tradeBookService.getAllTrades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TradeBook> getTradeById(@PathVariable Long id) {
        return tradeBookService.getTradeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TradeBook createTrade(@RequestBody TradeBook tradeBook) {
        return tradeBookService.saveTrade(tradeBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TradeBook> updateTrade(@PathVariable Long id, @RequestBody TradeBook tradeBookDetails) {
        return ResponseEntity.ok(tradeBookService.updateTrade(id, tradeBookDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrade(@PathVariable Long id) {
        tradeBookService.deleteTrade(id);
        return ResponseEntity.noContent().build();
    }
}
