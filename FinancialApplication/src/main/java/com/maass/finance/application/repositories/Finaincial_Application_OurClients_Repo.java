package com.maass.finance.application.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.maass.finance.application.collections.Financial_Application_OurClients;

@Repository
public interface Finaincial_Application_OurClients_Repo extends MongoRepository<Financial_Application_OurClients, String>{

}
