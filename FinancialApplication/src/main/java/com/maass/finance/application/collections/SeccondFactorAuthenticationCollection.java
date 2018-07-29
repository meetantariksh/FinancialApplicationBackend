package com.maass.finance.application.collections;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_seccond_factor_authentication")

public class SeccondFactorAuthenticationCollection {
	@Id ObjectId databaseId;
	int id;
	
	private String user_id = null;
	private String question_one_id = null;
	private String question_two_id = null;
	private String question_three_text = null;
	private String answer_one = null;
	private String answer_two = null;
	private String answer_three = null;
	private String email = null;
	private int question_failed_attempts = 0;
	private int email_failed_attempts = 0;
	public SeccondFactorAuthenticationCollection(String user_id,
			String question_one_id, String question_two_id,
			String question_three_text, String answer_one, String answer_two,
			String answer_three, String email, int question_failed_attempts,
			int email_failed_attempts) {
		super();
		this.user_id = user_id;
		this.question_one_id = question_one_id;
		this.question_two_id = question_two_id;
		this.question_three_text = question_three_text;
		this.answer_one = answer_one;
		this.answer_two = answer_two;
		this.answer_three = answer_three;
		this.email = email;
		this.question_failed_attempts = question_failed_attempts;
		this.email_failed_attempts = email_failed_attempts;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getQuestion_one_id() {
		return question_one_id;
	}
	public void setQuestion_one_id(String question_one_id) {
		this.question_one_id = question_one_id;
	}
	public String getQuestion_two_id() {
		return question_two_id;
	}
	public void setQuestion_two_id(String question_two_id) {
		this.question_two_id = question_two_id;
	}
	public String getQuestion_three_text() {
		return question_three_text;
	}
	public void setQuestion_three_text(String question_three_text) {
		this.question_three_text = question_three_text;
	}
	public String getAnswer_one() {
		return answer_one;
	}
	public void setAnswer_one(String answer_one) {
		this.answer_one = answer_one;
	}
	public String getAnswer_two() {
		return answer_two;
	}
	public void setAnswer_two(String answer_two) {
		this.answer_two = answer_two;
	}
	public String getAnswer_three() {
		return answer_three;
	}
	public void setAnswer_three(String answer_three) {
		this.answer_three = answer_three;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getQuestion_failed_attempts() {
		return question_failed_attempts;
	}
	public void setQuestion_failed_attempts(int question_failed_attempts) {
		this.question_failed_attempts = question_failed_attempts;
	}
	public int getEmail_failed_attempts() {
		return email_failed_attempts;
	}
	public void setEmail_failed_attempts(int email_failed_attempts) {
		this.email_failed_attempts = email_failed_attempts;
	}
	
}
