package com.example.portfoliomanager.rest;

import com.example.portfoliomanager.entity.FundManager;
import com.example.portfoliomanager.entity.FundTran;
import com.example.portfoliomanager.service.FundManagerService;
import com.example.portfoliomanager.service.FundTranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funds")
@CrossOrigin(origins = "*" ,methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class FundTranController {

    @Autowired
    private FundTranService fundTranService;

    @GetMapping
    public List<FundTran> getAllFundTrans() {
        return fundTranService.getAllFundTrans();
    }

    @GetMapping("/{id}")
    public FundTran getFundTranById(@PathVariable Integer id) {
        return fundTranService.getFundTranById(id);
    }

    @PostMapping
    public FundTran createFundTran(@RequestBody FundTran fundTran) {
        fundTranService.settleTransaction(fundTran);
        return fundTranService.saveFundTran(fundTran);
    }

    @PutMapping("/{id}")
    public FundTran updateFundTran(@PathVariable Integer id, @RequestBody FundTran fundTran) {
        FundTran existingFundTran = fundTranService.getFundTranById(id);
        if (existingFundTran != null) {
            fundTran.setRecId(id);
            return fundTranService.saveFundTran(fundTran);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFundTran(@PathVariable Integer id) {
        fundTranService.deleteFundTran(id);
    }
}
