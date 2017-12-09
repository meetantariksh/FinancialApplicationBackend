package com.maass.finance.application.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="financial_application_ourClients")
public class Financial_Application_OurClients {
	@Id
	public String id;
	
	public String name = null;
	public String occupation = null;
	public String recomendationQuote = null;
	public String imageUrl = null;
	
	public Financial_Application_OurClients(){
		
	}
	
	@Override
    public String toString() {
        return String.format(
                "SystemCheck[id=%s, name='%s', occupation='%s', occupation='%s', imageUrl='%s']",
                id, name, occupation, recomendationQuote, imageUrl);
    }
}
