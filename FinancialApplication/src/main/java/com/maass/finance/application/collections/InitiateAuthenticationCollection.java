package com.maass.finance.application.collections;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "initiate_authentication")

public class InitiateAuthenticationCollection {
	@Id ObjectId databaseId;
	int id;
	
	private String first_name = null;
	private String last_name = null;
	private String email = null;
	private String user_id = null;
	private String access_token = null;
	private String auth0_token = null;
	private String auth0_subject = null;
	private long token_last_update_time;
	
	public InitiateAuthenticationCollection(String first_name,
			String last_name, String email, String user_id,
			String access_token, String auth0_token, String auth0_subject,
			long token_last_update_time) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.user_id = user_id;
		this.access_token = access_token;
		this.auth0_token = auth0_token;
		this.auth0_subject = auth0_subject;
		this.token_last_update_time = token_last_update_time;
	}
	
	public InitiateAuthenticationCollection() {
		super();
	}

	public ObjectId getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(ObjectId databaseId) {
		this.databaseId = databaseId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getAuth0_token() {
		return auth0_token;
	}

	public void setAuth0_token(String auth0_token) {
		this.auth0_token = auth0_token;
	}

	public String getAuth0_subject() {
		return auth0_subject;
	}

	public void setAuth0_subject(String auth0_subject) {
		this.auth0_subject = auth0_subject;
	}

	public long getToken_last_update_time() {
		return token_last_update_time;
	}

	public void setToken_last_update_time(long token_last_update_time) {
		this.token_last_update_time = token_last_update_time;
	}
	
}
