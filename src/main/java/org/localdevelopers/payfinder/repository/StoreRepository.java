package org.localdevelopers.payfinder.repository;

import org.localdevelopers.payfinder.domain.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StoreRepository extends MongoRepository<Store, String> {
    List<Store> findByNameContaining(String keyword);
    List<Store> findBySiGunNameStartsWith(String payType);
    List<Store> findByTypeContaining(String storeType);
    List<Store> findByNameContainingAndSiGunNameStartsWith(String keyword, String payType);
    List<Store> findByNameContainingAndTypeContaining(String keyword, String storeType);
    List<Store> findBySiGunNameStartsWithAndTypeContaining(String payType, String storeType);
    List<Store> findByNameContainingAndSiGunNameStartsWithAndTypeContaining(String keyword, String payType, String storeType);
}
