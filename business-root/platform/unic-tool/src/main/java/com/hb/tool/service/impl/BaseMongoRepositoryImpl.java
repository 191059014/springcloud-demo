package com.hb.tool.service.impl;

import com.hb.tool.service.IBaseMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository("baseMongoRepository")
public class BaseMongoRepositoryImpl implements IBaseMongoRepository {

    @Autowired
    private MongoOperations mongoOperations;

    public void insert(Object entity,String collectionName){
        mongoOperations.insert(entity,collectionName);
    }



}
