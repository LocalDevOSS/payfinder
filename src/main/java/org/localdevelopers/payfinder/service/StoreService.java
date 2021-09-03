package org.localdevelopers.payfinder.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.localdevelopers.payfinder.domain.Store;
import org.localdevelopers.payfinder.exception.NotFoundException;
import org.localdevelopers.payfinder.repository.StoreRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
}
