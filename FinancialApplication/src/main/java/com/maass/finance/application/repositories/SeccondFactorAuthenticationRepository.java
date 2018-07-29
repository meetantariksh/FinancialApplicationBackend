package com.maass.finance.application.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.maass.finance.application.collections.SeccondFactorAuthenticationCollection;

@Repository
public interface SeccondFactorAuthenticationRepository extends MongoRepository<SeccondFactorAuthenticationCollection, Integer>{
	
	@Query("{ 'user_id' : ?0}")
	SeccondFactorAuthenticationCollection getSeccondFactorAuthenticationByUserId(String user_id);

}
