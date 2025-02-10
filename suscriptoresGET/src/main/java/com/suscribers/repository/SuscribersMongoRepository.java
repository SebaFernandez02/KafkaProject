package com.suscribers.repository;


import com.suscribers.model.Suscriber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SuscribersMongoRepository extends MongoRepository<Suscriber, UUID> {

}


