package org.localdevelopers.payfinder.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.localdevelopers.payfinder.domain.Store;
import org.localdevelopers.payfinder.dto.Filter;
import org.localdevelopers.payfinder.dto.StoreResponse;
import org.localdevelopers.payfinder.exception.NotFoundException;
import org.localdevelopers.payfinder.repository.StoreRepository;
import org.localdevelopers.payfinder.utils.LocationDistance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Log4j2
@AllArgsConstructor
public class StoreService {
    private StoreRepository storeRepository;
    private final static int MAX_SEARCH_SIZE = 200;

    //6131a5f6d381964989921b4d - example
    @Transactional
    public void getStoreInfo(final int storeIdx) {

        final Store store = storeRepository.findById("6131a5f6d381964989921b4d").orElseThrow(
                () -> new NotFoundException("StoreService.getStoreInfo"));
        log.info(store.toString());
    }

    public List<StoreResponse> getStoreFilter(final Filter filter) {
        if(filter.getKeyword() == null) {
            return getStoreNoKeyword(filter);
        }
        return getStoreWithKeyword(filter);
    }

    @Transactional
    public List<StoreResponse> getStoreNoKeyword(final Filter filter) {
        List<Store> stores;
        if(filter.getPayType() == null && filter.getStoreType() == null){
            stores = storeRepository.findAll();
        } else if(filter.getPayType() == null) {
            stores = storeRepository.findByTypeStartsWith(filter.getStoreType().getName());
        } else if(filter.getStoreType() == null) {
            stores = storeRepository.findBySiGunNameEquals(filter.getPayType().getName());
        } else {
            stores = storeRepository.findBySiGunNameEqualsAndTypeStartsWith(filter.getPayType().getName(), filter.getStoreType().getName());
        }
        return convertResponse(stores, filter);
    }

    @Transactional
    public List<StoreResponse> getStoreWithKeyword(final Filter filter) {
        List<Store> stores;
        if(filter.getPayType() == null && filter.getStoreType() == null) {
            stores = storeRepository.findByNameContaining(filter.getKeyword());
        } else if(filter.getPayType() == null) {
            stores = storeRepository.findByNameContainingAndTypeStartsWith(filter.getKeyword(), filter.getStoreType().getName());
        } else if(filter.getStoreType() == null) {
            stores = storeRepository.findByNameContainingAndSiGunNameEquals(filter.getKeyword(), filter.getPayType().getName());
        } else {
            stores = storeRepository.findByNameContainingAndSiGunNameEqualsAndTypeStartsWith(filter.getKeyword(), filter.getPayType().getName(), filter.getStoreType().getName());
        }

        return convertResponse(stores, filter);

    }

    private List<StoreResponse> convertResponse(List<Store> stores, Filter filter) {
        List<StoreResponse> storeResponses = new ArrayList<>();
        for (Store store : stores) {
            StoreResponse storeResponse = StoreResponse.builder()
                    .id(store.getId())
                    .sgName(store.getSiGunName())
                    .name(store.getName())
                    .type(store.getType())
                    .address(store.getRoadNameAddress())
                    .build();
            if(store.getLatitude() != null && store.getLongitude() != null) {
                storeResponse.setLatitude(store.getLatitude());
                storeResponse.setLongitude(store.getLongitude());
            }

            storeResponses.add(storeResponse);
        };

        storeResponses.stream().filter(storeResponse -> {
            if(filter.getLatitude() == null) {
                return true;
            }
            if(storeResponse.getLatitude() == null){
                return false;
            }
            Double dist = LocationDistance.distance(filter.getLatitude(),
                        filter.getLongitude(),
                        storeResponse.getLatitude(),
                        storeResponse.getLongitude());
            if(dist > 8) {
                return false;
            }
            return true;
        });

        return storeResponses.size() >= MAX_SEARCH_SIZE ?
                storeResponses.subList(0, MAX_SEARCH_SIZE):
                storeResponses.subList(0, storeResponses.size());
    }

}
