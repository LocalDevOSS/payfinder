package org.localdevelopers.payfinder.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.localdevelopers.payfinder.domain.Store;
import org.localdevelopers.payfinder.dto.Filter;
import org.localdevelopers.payfinder.dto.StoreResponse;
import org.localdevelopers.payfinder.exception.NotFoundException;
import org.localdevelopers.payfinder.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Log4j2
@AllArgsConstructor
public class StoreService {
    private StoreRepository storeRepository;

    //6131a5f6d381964989921b4d - example
    @Transactional
    public void getStoreInfo(final int storeIdx) {

        Store store = storeRepository.findById("6131a5f6d381964989921b4d").orElseThrow(
                () -> new NotFoundException("StoreService.getStoreInfo"));
        log.info(store.toString());
    }

    @Transactional
    public List<StoreResponse> getStoreFilter(final Filter filter) {
        List<Store> stores;
        if(filter.getPayType() == null && filter.getStoreType() == null){
            stores = storeRepository.findAll();
        } else if(filter.getPayType() == null) {
            stores = storeRepository.findByTypeContaining(filter.getStoreType());
        } else if(filter.getStoreType() == null) {
            stores = storeRepository.findBySiGunNameStartsWith(filter.getPayType());
        } else {
            stores = storeRepository.findBySiGunNameStartsWithAndTypeContaining(filter.getPayType(), filter.getStoreType());
        }
        return cnvtResponse(stores);
    }

    @Transactional
    public List<StoreResponse> getStoreKeywordAndFilter(final String keyword,
                             final Filter filter) {
        List<Store> stores;
        if(filter.getPayType() == null && filter.getStoreType() == null) {
            stores = storeRepository.findByNameContaining(keyword);
        } else if(filter.getPayType() == null) {
            stores = storeRepository.findByNameContainingAndTypeContaining(keyword, filter.getStoreType());
        } else if(filter.getStoreType() == null) {
            stores = storeRepository.findByNameContainingAndSiGunNameStartsWith(keyword, filter.getPayType());
        } else {
            stores = storeRepository.findByNameContainingAndSiGunNameStartsWithAndTypeContaining(keyword, filter.getPayType(), filter.getStoreType());
        }

        return cnvtResponse(stores);

    }

    private List<StoreResponse> cnvtResponse(List<Store> stores) {
        List<StoreResponse> storeResponses = new ArrayList<>();
        stores.forEach(store -> {
            storeResponses.add(new StoreResponse(
                    store.getId(),
                    store.getSiGunName(),
                    store.getName(),
                    store.getType(),
                    store.getRoadNameAddress(),
                    store.getLatitude().toString(),
                    store.getLongitude().toString()));

        });
        return storeResponses;
    }

}
