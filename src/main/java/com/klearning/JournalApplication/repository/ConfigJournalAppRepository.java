package com.klearning.JournalApplication.repository;

import com.klearning.JournalApplication.entity.ConfigJournalAppEntity;
import com.klearning.JournalApplication.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {


}
