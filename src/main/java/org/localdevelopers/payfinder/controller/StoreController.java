package org.localdevelopers.payfinder.controller;

import lombok.AllArgsConstructor;
import org.localdevelopers.payfinder.dto.Filter;
import org.localdevelopers.payfinder.dto.StoreResponse;
import org.localdevelopers.payfinder.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StoreController {

    private StoreService storeService;


    @GetMapping("stores/{store_idx}")
    public ResponseEntity getStoreInfo(@PathVariable("store_idx") final int storeIdx) {
        try {
            storeService.getStoreInfo(storeIdx);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @GetMapping("stores")
    public ResponseEntity getSearchRes(@RequestParam(value = "keyword", required = false) final String keyword,
                                       @RequestParam(value = "pay_type", required = false) final String payType,
                                       @RequestParam(value = "store_type", required = false) final String storeType) {
        Filter filter = new Filter();
        if(payType != null) {
            filter.setPayType(payType);
        } else if(storeType != null) {
            filter.setStoreType(storeType);
        }

        if(keyword == null) {
            return ResponseEntity.ok(storeService.getStoreFilter(filter));
        }

        return ResponseEntity.ok(storeService.getStoreKeywordAndFilter(keyword, filter));
    }

}
