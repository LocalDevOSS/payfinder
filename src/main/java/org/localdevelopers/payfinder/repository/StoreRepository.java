package org.localdevelopers.payfinder.repository;

import org.localdevelopers.payfinder.domain.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store, String> {
}
