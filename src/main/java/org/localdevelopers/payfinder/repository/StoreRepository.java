package org.localdevelopers.payfinder.repository;

import org.localdevelopers.payfinder.domain.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface StoreRepository extends MongoRepository<Store, String> {
    List<Store> findByNameContaining(String keyword);
    List<Store> findBySiGunNameEquals(String payType);
    List<Store> findByTypeStartsWith(String storeType);
    List<Store> findByNameContainingAndSiGunNameEquals(String keyword, String payType);
    List<Store> findByNameContainingAndTypeStartsWith(String keyword, String storeType);
    List<Store> findBySiGunNameEqualsAndTypeStartsWith(String payType, String storeType);
    List<Store> findByNameContainingAndSiGunNameEqualsAndTypeStartsWith(String keyword, String payType, String storeType);
}
