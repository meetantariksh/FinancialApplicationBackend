package com.maass.medApp.backEnd.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.maass.medApp.backEnd.Collections.MedApp_client_ourDoctors;

@Repository
public interface MedApp_client_ourDoctors_Repository extends MongoRepository<MedApp_client_ourDoctors, String>{

}