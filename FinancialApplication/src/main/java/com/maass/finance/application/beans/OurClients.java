package com.maass.finance.application.beans;

public class OurClients {
	private String name = null;
	private String occupation = null;
	private String recomendationQuote = null;
	private String imageUrl = null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQualification() {
		return occupation;
	}
	public void setQualification(String qualification) {
		this.occupation = qualification;
	}
	public String getRecomendationQuote() {
		return recomendationQuote;
	}
	public void setRecomendationQuote(String recomendationQuote) {
		this.recomendationQuote = recomendationQuote;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
