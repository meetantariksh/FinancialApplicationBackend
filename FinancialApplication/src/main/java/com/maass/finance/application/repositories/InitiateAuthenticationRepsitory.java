package com.maass.finance.application.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.maass.finance.application.collections.InitiateAuthenticationCollection;

@Repository
public interface InitiateAuthenticationRepsitory extends MongoRepository<InitiateAuthenticationCollection, Integer>{

}
