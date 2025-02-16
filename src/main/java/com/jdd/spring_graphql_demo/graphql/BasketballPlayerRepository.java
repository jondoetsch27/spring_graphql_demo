package com.jdd.spring_graphql_demo.graphql;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketballPlayerRepository extends MongoRepository<BasketballPlayer, String> {
    
}
