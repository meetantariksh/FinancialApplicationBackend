package com.maass.finance.application.collections;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "global_security_questions")

public class SecurityQuestionsCollections {
	@Id ObjectId databaseId;
	int id;
	
	private String question_group = null;
	private String question_id = null;
	private String question_text = null;
	public SecurityQuestionsCollections(String question_group,
			String question_id, String question_text) {
		super();
		this.question_group = question_group;
		this.question_id = question_id;
		this.question_text = question_text;
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
	public String getQuestion_group() {
		return question_group;
	}
	public void setQuestion_group(String question_group) {
		this.question_group = question_group;
	}
	public String getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_text() {
		return question_text;
	}
	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}
}
