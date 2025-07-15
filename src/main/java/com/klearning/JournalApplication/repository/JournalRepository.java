package com.klearning.JournalApplication.repository;

import com.klearning.JournalApplication.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalRepository extends MongoRepository<JournalEntity, ObjectId> {


}
