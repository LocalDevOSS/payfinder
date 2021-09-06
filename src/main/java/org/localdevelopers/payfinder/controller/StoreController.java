package org.localdevelopers.payfinder.controller;

import lombok.AllArgsConstructor;
import org.localdevelopers.payfinder.dto.Filter;
import org.localdevelopers.payfinder.dto.PayTypeField;
import org.localdevelopers.payfinder.dto.StoreResponse;
import org.localdevelopers.payfinder.dto.StoreTypeField;
import org.localdevelopers.payfinder.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.testng.annotations.Test;

@RestController
@AllArgsConstructor
public class StoreController {

    private final StoreService storeService;


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
                                       @RequestParam(value = "pay_type", required = false) final PayTypeField payType,
                                       @RequestParam(value = "store_type", required = false) final StoreTypeField storeType,
                                       @RequestParam(value = "latitude", required = false) final Double latitude,
                                       @RequestParam(value = "longitude", required = false) final Double longitude) {

        final Filter filter = Filter.builder()
                .keyword(keyword)
                .payType(payType)
                .storeType(storeType)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        return ResponseEntity.ok(storeService.getStoreFilter(filter));
    }



}
