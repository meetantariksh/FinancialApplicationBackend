package com.maass.medApp.backEnd.Collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="medApp_client_ourDoctors")
public class MedApp_client_ourDoctors {
	@Id
	public String id;
	
	public String name = null;
	public String qualification = null;
	public String recomendationQuote = null;
	public String imageUrl = null;
	
	public MedApp_client_ourDoctors(){
		
	}
	
	@Override
    public String toString() {
        return String.format(
                "SystemCheck[id=%s, name='%s', qualification='%s', recomendationQuote='%s', imageUrl='%s']",
                id, name, qualification, recomendationQuote, imageUrl);
    }
}