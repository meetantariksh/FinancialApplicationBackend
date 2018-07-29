package com.maass.finance.application.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.maass.finance.application.collections.SecurityQuestionsCollections;

@Repository
public interface SecurityQuestionsRepository extends MongoRepository<SecurityQuestionsCollections, Integer>{
	
	@Query("{ '$or' : [{'question_id' : ?0}, {'question_id' : ?1}] }")
	List<SecurityQuestionsCollections> getUserSecurityQuestions(String questionIDOne, String questionIDTwo);
	
}
