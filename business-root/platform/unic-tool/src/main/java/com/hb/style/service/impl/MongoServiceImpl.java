package com.hb.style.service.impl;

import com.hb.style.service.IMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * mongodb操作类
 */
@Service("mongoService")
public class MongoServiceImpl implements IMongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertMongoEntity(Object obj, String collectionName) {
        mongoTemplate.insert(obj, collectionName);
    }

}
