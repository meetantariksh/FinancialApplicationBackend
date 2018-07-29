package com.maass.finance.application.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.maass.finance.application.collections.InitiateAuthenticationCollection;

@Repository
public interface InitiateAuthenticationRepsitory extends MongoRepository<InitiateAuthenticationCollection, Integer>{
	
	
	@Query("{ 'auth0_token' : ?0, 'auth0_subject' : ?1, 'access_token' : ?2 }")
	InitiateAuthenticationCollection getInitiatAuthenticationCollection(String auth0Token, String auth0Subject, String accessToken);
}
