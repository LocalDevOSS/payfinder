package org.localdevelopers.payfinder.controller;

import lombok.AllArgsConstructor;
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
    public ResponseEntity getSearchRes(@RequestParam("keyword") final String keyword,
                                       @RequestParam("pay_type") final String payType,
                                       @RequestParam("store_type") final String storeType) {

        return ResponseEntity.ok().build();
    }

}
