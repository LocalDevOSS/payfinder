package org.localdevelopers.payfinder.repository;

import org.localdevelopers.payfinder.dto.TestDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempRepository extends MongoRepository<TestDto, Long> {
}
