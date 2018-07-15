package com.maass.finance.application.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.maass.finance.application.collections.UserProfileCollection;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfileCollection, Integer>{
	
	@Query("{ 'email' : ?0 }")
	UserProfileCollection getUserProfileFromEmail(String email);
}
