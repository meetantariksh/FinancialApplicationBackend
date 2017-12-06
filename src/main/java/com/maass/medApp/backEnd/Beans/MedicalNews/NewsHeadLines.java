package com.maass.medApp.backEnd.Beans.MedicalNews;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsHeadLines {
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("articles")
	@Expose
	private List<NewsHeadlinesArticle> articles = null;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<NewsHeadlinesArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<NewsHeadlinesArticle> articles) {
		this.articles = articles;
	}
}
